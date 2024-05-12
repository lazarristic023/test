package com.example.Project.Controller;


import com.example.Project.Dto.UserDto;
import com.example.Project.Model.Role;
import com.example.Project.Model.User;
import com.example.Project.Model.UserTokenState;
import com.example.Project.Service.UserService;
import com.example.Project.Utilities.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        String refreshToken = tokenUtils.generateTokens(user.getUsername(),rolesString, user.getId())[0];
        int expiresIn = tokenUtils.getACCESS_TOKEN_EXPIRES_IN();
        int refreshExpiresIn = tokenUtils.getREFRESH_TOKEN_EXPIRES_IN();


        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,refreshToken,refreshExpiresIn));
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken,String username,String password) {
        try {
            // Dobijanje novog access tokena na osnovu refresh tokena
            String newAccessToken = tokenUtils.generateNewAccessToken(refreshToken,username, password);
            // Vraćanje novog access tokena kao odgovor na zahtjev
            return ResponseEntity.ok(newAccessToken);
        } catch (IllegalArgumentException e) {
            // Ako je refresh token nevažeći, vratite odgovarajući status greške
            return ResponseEntity.badRequest().body("Neuspješno osvježavanje tokena. Refresh token nije validan.");
        }
    }

}