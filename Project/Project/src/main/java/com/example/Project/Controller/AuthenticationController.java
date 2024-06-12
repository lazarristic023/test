package com.example.Project.Controller;


import com.example.Project.Dto.RefreshTokenRequest;
import com.example.Project.Dto.LoginDto;
import com.example.Project.Dto.UserDto;
import com.example.Project.Enum.PackageType;
import com.example.Project.Enum.Role;
import com.example.Project.Model.EmailToken;
import com.example.Project.Model.Request;
import com.example.Project.Model.User;
import com.example.Project.Model.UserTokenState;
import com.example.Project.Service.EmailTokenService;
import com.example.Project.Service.RequestService;
import com.example.Project.Service.UserService;
import com.example.Project.Model.*;
import com.example.Project.Service.*;
import com.example.Project.Service.impl.UserServiceImpl;
import com.example.Project.Utilities.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordlessTokenService passwordlessTokenService;

    @Autowired
    private EmailTokenService emailTokenService;

    @Autowired
    private RequestService requestService;
    private TwoFactorAuthenticationService tfaService;


    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody LoginDto authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        //ubacuje se korisnik u trenutni security context
        SecurityContextHolder.getContext().setAuthentication(authentication);


        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();
        List<String> rolesString = new ArrayList<>();

        rolesString.add(role.toString());


        if (role.equals(Role.CLIENT)) {
            Client client = clientService.getById(user.getId());
            if (client.isTfaEnabled()) {
                return ResponseEntity.ok(new UserTokenState("", 0,"",0, true, ""));
            }
        }


        String jwt = tokenUtils.generateTokens(user.getUsername(),rolesString, user.getId())[0];
        String refreshToken = tokenUtils.generateTokens(user.getUsername(),rolesString, user.getId())[1];
        int expiresIn = tokenUtils.getACCESS_TOKEN_EXPIRES_IN();
        int refreshExpiresIn = tokenUtils.getREFRESH_TOKEN_EXPIRES_IN();


        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,refreshToken,refreshExpiresIn, false, ""));
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenRequest> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        String username = refreshTokenRequest.getUsername();
        String password = refreshTokenRequest.getPassword();
        try {
            // Dobijanje novog access tokena na osnovu refresh tokena
            String newAccessToken = tokenUtils.generateNewAccessToken(refreshToken,username, password);
            RefreshTokenRequest request = new RefreshTokenRequest();
            request.setRefreshToken(newAccessToken);
            request.setPassword("lala");
            request.setUsername(username);
            // Vraćanje novog access tokena kao odgovor na zahtjev
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException e) {
            // Ako je refresh token nevažeći, vratite odgovarajući status greške
            return ResponseEntity.badRequest().body(refreshTokenRequest);
        }
    }

    @RequestMapping(value="/verify", method = RequestMethod.GET)
    public ResponseEntity<?> verifyAccount(@Param("email")String email, @Param("id")Long id,@Param("expiry") Long expiry,@Param("token")String token,HttpServletResponse response) throws IOException {

        EmailToken emailToken=emailTokenService.getByClientId(id);
        //vec bio na linku
        if(emailToken.getIsUsed() ){
            response.sendRedirect("https://localhost:4200/email-link-invalid");
        }else{
            //nije bio na linku ali je token istekao
            if(emailToken.getExpirationDate().isBefore(LocalDateTime.now())){
                response.sendRedirect("https://localhost:4200/email-link-invalid");
            }else{
                //nije bio na linku i token nije istekao
                Client client=clientService.getById(id);
                client.setEmailChecked(true);
                userService.save(client);

                String secretImageUrl = "";
                if(client.isTfaEnabled()) {
                    secretImageUrl = tfaService.generateQrCodeImageUri(client.getSecret());
                }
                //update token
                emailToken.setIsUsed(true);
                emailTokenService.saveToken(emailToken);
//                String redirectUrl = String.format("http://localhost:4200/successfully/%s/%s", client.getEmail(), secretImageUrl);
//                response.sendRedirect(redirectUrl);
                UserTokenState tokenState = new UserTokenState("",0,"",0,true, secretImageUrl);

                return ResponseEntity.ok(tokenState);
            }

        }




        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping ("/sendPasswordlessLoginLink")
    public ResponseEntity<Boolean> sendPasswordlessLoginLink(@Param("email")String email, HttpServletResponse response) throws IOException {

        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (user.getRole().equals(Role.CLIENT)) {
            Client client = clientService.getById(user.id);
            if (client.getPackageType().equals(PackageType.BASIC)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        emailService.sendPasswordlessLoginLink(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/passwordlessLogin")
    public ResponseEntity<Boolean> passwordlessLogin(@Param("token")String token, HttpServletResponse response) throws IOException {

        PasswordlessToken passwordlessToken = passwordlessTokenService.findByToken(token);
        if (passwordlessToken == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (passwordlessToken.isUsed()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Date now = new Date();
        Date tokenExpiration = tokenUtils.getExpirationDateFromToken(token);
        if (tokenExpiration.compareTo(now) < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        passwordlessToken.setUsed(true);
        passwordlessTokenService.saveToken(passwordlessToken);
        String email = tokenUtils.getUsernameFromToken(token);
        //UserDetails userDetails = userService.findByEmail(email);
        User user = userService.findByEmail(email);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        List<String> rolesString = new ArrayList<>();
        rolesString.add(user.getRole().toString());

        String[] tokens = tokenUtils.generateTokens(user.getUsername(), rolesString, user.id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Token", tokens[0]);
        headers.add("Refresh-Token", tokens[1]);

        return ResponseEntity.ok().headers(headers).build();

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/isEmailChecked/{id}")
    public Boolean isEmailChecked(@PathVariable("id") Long id){

        User user= userService.getById(id);
        if(user.getEmailChecked()){
            return true;
        }else{
            return false;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable("id") Long id){
        User user= userService.getById(id);

        UserDto userDto= new UserDto(user.getUsername(),user.getEmail(),user.getPassword()
                ,user.getRole().toString(),user.getEmailChecked());

        userDto.setId(user.getId());

        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updateEmployee")
    public ResponseEntity<UserDto> updateEmployee(@RequestBody UserDto userr){


        User existing = userService.getById(userr.getId());
        Request request = requestService.getByClientId(existing.getUsername());
        existing.setUsername(userr.getUsername());
        existing.setEmailChecked(true);
        request.setUsername(userr.getUsername());
        userService.save(existing);
        requestService.create(request);


        return new ResponseEntity<>(userr,HttpStatus.OK);
    }

    @PostMapping("/verifyTfaCode")
    public ResponseEntity<?> verifyTfaCode(
            @RequestBody TfaCodeVerificationRequest verificationRequest
    ) {
        Client client = clientService.getByEmail(verificationRequest.getEmail());
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (tfaService.isOtpNotValid(client.getSecret(), verificationRequest.getCode())) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<String> rolesString = new ArrayList<>();
        rolesString.add(client.getRole().toString());
        String[] tokens = tokenUtils.generateTokens(client.getUsername(), rolesString, client.id);

        return ResponseEntity.ok(new UserTokenState(tokens[0], 1,tokens[1],1, client.isTfaEnabled(), ""));

    }

}