package com.example.Project.Service;

import com.example.Project.Model.Administrator;
import com.example.Project.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User findByUsername(String name);
    public User findByEmail(String email);
    public User save(User user);
    public User getById(Long id);
    public void updateEmail(Long id, String email);

    public List<User> findAll();

    public List<User> getAllClients();
    public List<User> getAllEmployees();
    public Optional<Administrator> getAdminById(Long id);
    public Administrator updateAdmin(Long id, Administrator updatedAdmin);
}
