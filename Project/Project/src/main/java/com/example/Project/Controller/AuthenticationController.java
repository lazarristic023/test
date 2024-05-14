package com.example.Project.Controller;


import com.example.Project.Dto.RefreshTokenRequest;
import com.example.Project.Dto.UserDto;
import com.example.Project.Enum.Role;
import com.example.Project.Model.User;
import com.example.Project.Model.UserTokenState;
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


    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody UserDto authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        //ubacuje se korisnik u trenutni security context
        SecurityContextHolder.getContext().setAuthentication(authentication);


        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();
        List<String> rolesString = new ArrayList<>();

        rolesString.add(role.toString());


        String jwt = tokenUtils.generateTokens(user.getUsername(),rolesString, user.getId())[0];
        String refreshToken = tokenUtils.generateTokens(user.getUsername(),rolesString, user.getId())[1];
        int expiresIn = tokenUtils.getACCESS_TOKEN_EXPIRES_IN();
        int refreshExpiresIn = tokenUtils.getREFRESH_TOKEN_EXPIRES_IN();


        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,refreshToken,refreshExpiresIn));
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
    public ResponseEntity<Boolean> verifyAccount(@Param("email")String email, @Param("id")Long id, HttpServletResponse response) throws IOException {

            response.sendRedirect("http://localhost:4200/email");
            User client=userService.getById(id);
            client.setEmailChecked(true);
            userService.save(client);
            return new ResponseEntity<>(HttpStatus.OK);

    }

}