package com.example.Project.Service.impl;


import com.example.Project.Model.User;
import com.example.Project.Repository.UserRepo;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    public User findByUsername(String name) {return userRepo.findByUsername(name);}

    public User save(User user){
        return userRepo.save(user);
    }

    public User getById(Long id){

        return userRepo.getById(id);
    }
}
