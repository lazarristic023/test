package com.example.Project.Service;

import com.example.Project.Dto.ChangePasswordRequest;
import com.example.Project.Dto.ResetPasswordRequest;
import com.example.Project.Model.Administrator;
import com.example.Project.Model.Employee;
import com.example.Project.Model.User;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public User findByUsername(String name);
    public User findByEmail(String email);
    public User save(User user);
    public User getById(Long id);
    //public void updateEmail(Long id, String email);

    public List<User> findAll();

    public List<User> getAllClients();
    public List<User> getAllEmployees();
    public Optional<Administrator> getAdminById(Long id);
    public Optional<Employee> getEmployeeById(Long id);
    public Administrator updateAdmin(Long id, Administrator updatedAdmin);
    void updateEmail(Long id, String email);
    void updateUsername(Long id, String username);

    public Administrator registerAdmin(Administrator administrator);
    public Employee registerEmployee(Employee employee);
    public Employee updateEmployee(Long id, Employee updatedEmployee);

    public void changePassword(ChangePasswordRequest request);
    public void resetPassword(ResetPasswordRequest request);
}
