package com.example.Project.Service;


import com.example.Project.Model.PasswordlessToken;
import com.example.Project.Model.EmailToken;
import com.example.Project.Model.User;
import com.example.Project.Utilities.TokenUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class EmailService {

    // Tajni ključ za potpisivanje tokena
    private static final String SECRET_KEY = "secret";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordlessTokenService passwordlessTokenService;

    @Autowired
    private EmailTokenService emailTokenService;



    public String sendEmail(User user)  throws NoSuchAlgorithmException, InvalidKeyException, MessagingException, UnsupportedEncodingException{
        String subject = "Complete Registration";
        String token = generateToken(user);

        LocalDateTime expiryDateTime = LocalDateTime.now().plusMinutes(2);
        long expiryTimestamp = expiryDateTime.toEpochSecond(ZoneOffset.UTC);

        // Dodajte vremensko ograničenje u link
        //String link = "http://localhost:8081/api/authentication/verify?email=" + user.getEmail() + "&id=" + user.getId() + "&expiry=" + expiryTimestamp;

        String link = "http://localhost:4200/confirmAccount?email=" + user.getEmail() + "&id=" + user.getId() + "&expiry=" + expiryTimestamp;
        // Dodajte token u link
        link += "&token=" + token;

        EmailToken emailToken= new EmailToken(expiryDateTime,user.getId(),token,false);
        emailTokenService.saveToken(emailToken);

        String text = "To confirm your account, please click here : " + link;
        try {
            sendMess(user, subject,text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return link;
    }

    public void sendRejectedEmail(User user,String reason)  {
        String subject = "Registration denied";

        String titile="Registration";
        String text ="Your registration has been denied  " + reason ;

        try {
            sendMess(user, subject,text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    private void sendMess(User user, String subject, String text)  throws MessagingException, IOException{

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isaisaprojekat.com");

        helper.setTo(user.getEmail());
        helper.setSubject(subject);


        helper.setText(text);
        javaMailSender.send(message);
    }

    public void sendPasswordlessLoginLink(User user) {
        String subject = "Passwordless login";

        try {
            String token = tokenUtils.generatePasswordlessToken(user.getEmail());
            PasswordlessToken passwordlessToken = new PasswordlessToken(token, false);
            passwordlessTokenService.saveToken(passwordlessToken);
            String text ="Log in by clicking on the link: " + "http://localhost:4200/redirectPasswordlessLogin?token=" + token;

            sendMess(user, subject,text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateToken(User user) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        // Generišite jedinstveni token koji sadrži korisnički ID i vrijeme generisanja
        String data = user.getId() + ":" + LocalDateTime.now().toString()+ ":false";

        // Potpišite token HMAC algoritmom koristeći tajni ključ
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "HmacSHA256");
        sha256Hmac.init(secretKey);
        byte[] hmacData = sha256Hmac.doFinal(data.getBytes("UTF-8"));

        // Konvertujte potpisani token u string koristeći Base64 enkodiranje
        return Base64.getEncoder().encodeToString(hmacData);
    }
}
