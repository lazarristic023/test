package com.example.Project.Service.impl;


import com.example.Project.Model.Administrator;
import com.example.Project.Model.Employee;
import com.example.Project.Model.User;
import com.example.Project.Repository.AdminRepo;
import com.example.Project.Repository.EmployeeRepo;
import com.example.Project.Repository.UserRepo;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private EmployeeRepo employeeRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String name) {return userRepo.findByUsername(name);}

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

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }


    public Administrator updateAdmin(Long id, Administrator updatedAdmin) {
        return adminRepo.findById(id).map(admin -> {
            admin.setFirstName(updatedAdmin.getFirstName());
            admin.setLastName(updatedAdmin.getLastName());
            admin.setUsername(updatedAdmin.getUsername());
            admin.setEmail(updatedAdmin.getEmail());
            admin.setFirstLogging(updatedAdmin.isFirstLogging());
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

    
    public void updateUsername(Long id, String username) {
        userRepo.updateUsernameById(id, username);
    }
    
    @Override
    public Administrator registerAdmin(Administrator administrator) {
        String hashedPassword = passwordEncoder.encode(administrator.getPassword());
        administrator.setPassword(hashedPassword);
        return adminRepo.save(administrator);
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepo.findById(id).map(employee -> {
            employee.setUsername(updatedEmployee.getUsername());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPassword(updatedEmployee.getPassword());
            employee.setRole(updatedEmployee.getRole());
            employee.setCity(updatedEmployee.getCity());
            employee.setCountry(updatedEmployee.getCountry());
            employee.setPhone(updatedEmployee.getPhone());
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setFirstLogging(updatedEmployee.isFirstLogging());
            return employeeRepo.save(employee);
        }).orElseGet(() -> {
            updatedEmployee.setId(id);
            return employeeRepo.save(updatedEmployee);
        });
    }
}
