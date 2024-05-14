package com.example.Project.Service;

import com.example.Project.Model.EmailToken;
import com.example.Project.Repository.EmailTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailTokenService {

    @Autowired
    public EmailTokenRepo repo;

    public EmailToken saveToken(EmailToken emailToken){
        return repo.save(emailToken);
    }

    public EmailToken getByClientId(Long clientId){
        return repo.findTokenByClientId(clientId);
    }
}
