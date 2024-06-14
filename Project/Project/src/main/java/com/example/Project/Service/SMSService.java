package com.example.Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SMSService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendSMS(String phoneNumber, String message) {
        // Implementacija slanja SMS poruke preko SMS gateway servisa
        String url = "http://sms-gateway-url/send-sms";
        String requestBody = "{\"phoneNumber\": \"" + phoneNumber + "\", \"message\": \"" + message + "\"}";
        restTemplate.postForObject(url, requestBody, String.class);
    }
}
