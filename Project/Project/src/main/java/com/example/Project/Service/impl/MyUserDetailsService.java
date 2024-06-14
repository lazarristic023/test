package com.example.Project.Service.impl;

import com.example.Project.Model.User;
import com.example.Project.Repository.UserRepo;
import com.example.Project.Utilities.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepo.findByUsername(AESUtil.encrypt(username));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("User:");

        try {
            user.setUsername(AESUtil.decrypt(user.getUsername()));
            user.setEmail(AESUtil.decrypt(user.getEmail()));
            user.setCity(AESUtil.decrypt(user.getCity()));
            user.setCountry(AESUtil.decrypt(user.getCountry()));
            user.setPhone(AESUtil.decrypt(user.getPhone()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(user);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }
}