package com.example.Project.Service;


import com.example.Project.Model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;



    public void sendEmail(User user)  {
        String subject = "Complete Registration";

        String titile="Comfirm your email";
        String text ="To confirm your account, please click here : " + "http://localhost:8081/api/authentication/verify?email=" + user.getEmail()+ "&id=" + user.getId();

        try {
            sendMess(user, subject,text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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
}
