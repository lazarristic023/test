package com.example.Project.Service;

import com.example.Project.Model.Administrator;
import com.example.Project.Model.Employee;
import com.example.Project.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User findByUsername(String name) throws Exception;
    public User findByEmail(String email) throws Exception;
    public User save(User user) throws Exception;
    public User getById(Long id) throws Exception;
    //public void updateEmail(Long id, String email);

    public List<User> findAll() throws Exception;

    public List<User> getAllClients() throws Exception;
    public List<User> getAllEmployees() throws Exception;
    public Optional<Administrator> getAdminById(Long id);
    public Optional<Employee> getEmployeeById(Long id);
    public Administrator updateAdmin(Long id, Administrator updatedAdmin);
    void updateEmail(Long id, String email) throws Exception;
    void updateUsername(Long id, String username) throws Exception;

    public Administrator registerAdmin(Administrator administrator) throws Exception;
    public Employee registerEmployee(Employee employee) throws Exception;
    public Employee updateEmployee(Long id, Employee updatedEmployee);

    public int DeleteClient(Long id);
}
