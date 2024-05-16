package com.example.Project.Service.impl;


import com.example.Project.Model.Administrator;
import com.example.Project.Model.User;
import com.example.Project.Repository.AdminRepo;
import com.example.Project.Repository.UserRepo;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdminRepo adminRepo;
    public User findByUsername(String name) {return userRepo.findByUsername(name);}
    public User findByEmail(String email) {return userRepo.findByEmail(email);}

    public User save(User user){
        return userRepo.save(user);
    }

    public User getById(Long id){

        return userRepo.getById(id);
    }


    @Override
    public void updateEmail(Long id, String email) {
        userRepo.updateEmailById(id, email);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getAllClients() {
        List<User> clients = new ArrayList<>();
        for (User user:findAll()) {
            if(user.getRole().toString().equals("CLIENT")){
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public List<User> getAllEmployees() {
        List<User> clients = new ArrayList<>();
        for (User user:findAll()) {
            if(Objects.equals(user.getRole().toString(), "EMPLOYEE")){
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public Optional<Administrator> getAdminById(Long id) {
        return adminRepo.findById(id);
    }


    public Administrator updateAdmin(Long id, Administrator updatedAdmin) {
        return adminRepo.findById(id).map(admin -> {
            admin.setFirstName(updatedAdmin.getFirstName());
            admin.setLastName(updatedAdmin.getLastName());
            admin.setUsername(updatedAdmin.getUsername());
            admin.setEmail(updatedAdmin.getEmail());
            if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
                admin.setPassword(updatedAdmin.getPassword()); // Pretpostavimo da je password već hashed
            }
            admin.setPredefined(updatedAdmin.isPredefined());
            admin.setRole(updatedAdmin.getRole());
            admin.setEmailChecked(updatedAdmin.getEmailChecked());
            return adminRepo.save(admin);
        }).orElseGet(() -> {
            updatedAdmin.setId(id);
            return adminRepo.save(updatedAdmin);
        });
    }
}
