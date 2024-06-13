package com.example.Project.Utilities;


import com.example.Project.Dto.UserDto;
import com.example.Project.Service.impl.MyUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TokenUtils {

    @Value("spring-security-example")
    private String APP_NAME;

    //private MyUserDetailsService userDetailsService;

    @Value("somesecret")
    public String SECRET;


    /*@Getter
    @Value("900000")
    private int REFRESH_TOKEN_EXPIRES_IN;

    @Getter
    @Value("100000")
    private int ACCESS_TOKEN_EXPIRES_IN;*/

    @Getter
    @Value("1500000")
    private int REFRESH_TOKEN_EXPIRES_IN;

    @Getter
    @Value("900000")
    private int ACCESS_TOKEN_EXPIRES_IN;

    @Getter
    @Value("600000")
    private int PASSWORDLESS_TOKEN_EXPIRES_IN;


    @Value("Authorization")
    private String AUTH_HEADER;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private static final String AUDIENCE_WEB = "web";

    private String generateAudience() {

        //	Moze se iskoristiti org.springframework.mobile.device.Device objekat za odredjivanje tipa uredjaja sa kojeg je zahtev stigao.
        //	https://spring.io/projects/spring-mobile

        //	String audience = AUDIENCE_UNKNOWN;
        //		if (device.isNormal()) {
        //			audience = AUDIENCE_WEB;
        //		} else if (device.isTablet()) {
        //			audience = AUDIENCE_TABLET;
        //		} else if (device.isMobile()) {
        //			audience = AUDIENCE_MOBILE;
        //		}

        return AUDIENCE_WEB;
    }




    //generisanje tokena za usera sa prosledjenim username-om
    public String[] generateTokens(String username, List<String> roles, long id) {
        String refreshToken = Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(generateAudience())
                .claim("role", roles)
                .claim("id", id)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(generateRefreshTokenExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();

        String accessToken = Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(generateAudience())
                .claim("role", roles)
                .claim("id", id)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(generateAccessTokenExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();

        return new String[]{accessToken, refreshToken};
    }

    private Date generateRefreshTokenExpirationDate() {
        // Postavite datum isteka refresh tokena na duži period od access tokena
        return new Date(new Date().getTime() + REFRESH_TOKEN_EXPIRES_IN);
    }

    private Date generateAccessTokenExpirationDate() {
        return new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRES_IN);
    }


    public String generateNewAccessToken(String refreshToken,String username, String password) {
        // Provjerite da li je refresh token validan
        if (validateTokenForRefresh(refreshToken,username,password)) {
            // Dekodirajte refresh token kako biste dobili informacije o korisniku
            String usernamee = getUsernameFromToken(refreshToken);
            List<String> roles = getRolesFromToken(refreshToken);
            long userId = getUserIdFromToken(refreshToken);

            // Generišite novi access token za korisnika
            return generateToken(usernamee, roles, userId);
        } else {
            throw new IllegalArgumentException("Refresh token nije validan.");        }
    }


    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    public String generateToken(String username, List<String> roles, long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRES_IN);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", roles)
                .claim("id", userId)
                .claim("username", username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String generatePasswordlessToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + PASSWORDLESS_TOKEN_EXPIRES_IN);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public long getUserIdFromToken(String token) {
        long userId;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            userId = claims.get("id", Long.class);
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            userId = 0; // Defaultna vrijednost za slučaj greške
        }

        return userId;
    }

    //preuzimanje tokena
    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);


        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);

        }

        return null;
    }

    //preuzimanje korisnickog imena iz tokena
    public String getUsernameFromToken(String token) {
        String username;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    public List<String> getRolesFromToken(String token) {
        List<String> roles;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            roles = claims.get("role", List.class);
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            roles = null;
        }

        return roles;
    }

    //preuzimanje datuma kreiranja tokena
    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }


    //preuzimanje datuma do kog vazi token
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            expiration = null;
        }

        return expiration;
    }

    //citanje svih podataka iz tokena
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            claims = null;
        }


        return claims;
    }



    public Boolean validateToken(String token, UserDetails userr) {
       // UserDto user = (UserDto) userr;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);


        return (username != null
                && username.equals(userr.getUsername())
        );
    }


    public Boolean validateTokenForRefresh(String token, String username, String password) {
        UserDto user = new UserDto();
        user.setPassword(password);
        user.setUsername(username);
        final String usernamee = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);


        return (usernamee != null
                && usernamee.equals(user.getUsername())
        );
    }


    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }


}