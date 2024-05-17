package com.example.Project.Controller;


import com.example.Project.Dto.LoginDto;
import com.example.Project.Dto.UserDto;
import com.example.Project.Enum.Role;
import com.example.Project.Model.EmailToken;
import com.example.Project.Model.Request;
import com.example.Project.Model.User;
import com.example.Project.Model.UserTokenState;
import com.example.Project.Service.EmailTokenService;
import com.example.Project.Service.RequestService;
import com.example.Project.Service.UserService;
import com.example.Project.Utilities.TokenUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private EmailTokenService emailTokenService;

    @Autowired
    private RequestService requestService;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody LoginDto authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        //ubacuje se korisnik u trenutni security context
        SecurityContextHolder.getContext().setAuthentication(authentication);


        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();
        List<String> rolesString = new ArrayList<>();


        rolesString.add(role.toString());


        String jwt = tokenUtils.generateToken(user.getUsername(),rolesString, user.getId());
        int expiresIn = tokenUtils.getExpiredIn();


        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        // return ResponseEntity.ok("great");
    }

    @RequestMapping(value="/verify", method = RequestMethod.GET)
    public ResponseEntity<Boolean> verifyAccount(@Param("email")String email, @Param("id")Long id,@Param("expiry") Long expiry,@Param("token")String token,HttpServletResponse response) throws IOException {

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
                User client=userService.getById(id);
                client.setEmailChecked(true);
                userService.save(client);

                //update token
                emailToken.setIsUsed(true);
                emailTokenService.saveToken(emailToken);
                String redirectUrl = String.format("https://localhost:4200/successfully/%s/%d/%d/%s", email, id, expiry, token);
                response.sendRedirect(redirectUrl);
            }

        }




        return new ResponseEntity<>(HttpStatus.OK);

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

}