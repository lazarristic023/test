package com.example.Project.Service;

import com.example.Project.Model.User;

public interface UserService {

    public User findByUsername(String name);
    public User save(User user);
    public User getById(Long id);
    public void updateEmail(Long id, String email);
}
