package com.example.Project.Service;

import com.example.Project.Model.PasswordlessToken;
import com.example.Project.Repository.PasswordlessTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordlessTokenService {

    @Autowired
    private PasswordlessTokenRepo tokenRepo;

    public PasswordlessToken findByToken(String token) {
        return  tokenRepo.findByToken(token);
    }


    public PasswordlessToken saveToken(PasswordlessToken token) {
        return  tokenRepo.save(token);
    }

}
