package com.example.Project.Service.impl;


import com.example.Project.Model.Administrator;
import com.example.Project.Model.Employee;
import com.example.Project.Model.User;
import com.example.Project.Repository.AdminRepo;
import com.example.Project.Repository.EmployeeRepo;
import com.example.Project.Repository.UserRepo;
import com.example.Project.Service.UserService;
import com.example.Project.Utilities.AESUtil;
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

    public User findByUsername(String name) throws Exception {
        User user = userRepo.findByUsername(AESUtil.encrypt(name));
        if (user != null) {
            user.setUsername(AESUtil.decrypt(user.getUsername()));
            user.setEmail(AESUtil.decrypt(user.getEmail()));
            user.setPhone(AESUtil.decrypt(user.getPhone()));
        }
        return user;
    }

    public User findByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(AESUtil.encrypt(email));
        if (user != null) {
            user.setUsername(AESUtil.decrypt(user.getUsername()));
            user.setEmail(AESUtil.decrypt(user.getEmail()));
            user.setPhone(AESUtil.decrypt(user.getPhone()));
        }
        return user;
    }

    public User save(User user) throws Exception {
        user.setUsername(AESUtil.encrypt(user.getUsername()));
        user.setEmail(AESUtil.encrypt(user.getEmail()));
        user.setPhone(AESUtil.encrypt(user.getPhone()));
        user.setPassword(AESUtil.encrypt(user.getPassword()));
        return userRepo.save(user);
    }

    public User getById(Long id) throws Exception {
        User user = userRepo.getById(id);
        if (user != null) {
            user.setUsername(AESUtil.decrypt(user.getUsername()));
            user.setEmail(AESUtil.decrypt(user.getEmail()));
            user.setPhone(AESUtil.decrypt(user.getPhone()));
        }
        return user;
    }

    @Override
    public void updateEmail(Long id, String email) throws Exception {
        userRepo.updateEmailById(id, AESUtil.encrypt(email));
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            user.setUsername(AESUtil.decrypt(user.getUsername()));
            user.setEmail(AESUtil.decrypt(user.getEmail()));
            user.setPhone(AESUtil.decrypt(user.getPhone()));
        }
        return users;
    }

    @Override
    public List<User> getAllClients() throws Exception {
        List<User> clients = new ArrayList<>();
        List<User> allUsers = findAll();
        for (User user : allUsers) {
            if ("CLIENT".equals(user.getRole().toString())) {
                user.setUsername(AESUtil.decrypt(user.getUsername()));
                user.setEmail(AESUtil.decrypt(user.getEmail()));
                user.setPhone(AESUtil.decrypt(user.getPhone()));
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public List<User> getAllEmployees() throws Exception {
        List<User> clients = new ArrayList<>();
        for (User user:findAll()) {
            if(Objects.equals(user.getRole().toString(), "EMPLOYEE")){
                user.setUsername(AESUtil.decrypt(user.getUsername()));
                user.setEmail(AESUtil.decrypt(user.getEmail()));
                user.setPhone(AESUtil.decrypt(user.getPhone()));
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public Optional<Administrator> getAdminById(Long id) {
        Optional<Administrator> optionalAdmin = adminRepo.findById(id);
        optionalAdmin.ifPresent(admin -> {
            try {
                admin.setUsername(AESUtil.decrypt(admin.getUsername()));
                admin.setEmail(AESUtil.decrypt(admin.getEmail()));
                admin.setPhone(AESUtil.decrypt(admin.getPhone()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return optionalAdmin;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        optionalEmployee.ifPresent(employee -> {
            try {
                employee.setUsername(AESUtil.decrypt(employee.getUsername()));
                employee.setEmail(AESUtil.decrypt(employee.getEmail()));
                employee.setPhone(AESUtil.decrypt(employee.getPhone()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return optionalEmployee;
    }

    public Administrator updateAdmin(Long id, Administrator updatedAdmin) {
        return adminRepo.findById(id).map(admin -> {
            admin.setFirstName(updatedAdmin.getFirstName());
            admin.setLastName(updatedAdmin.getLastName());
            admin.setFirstLogging(updatedAdmin.isFirstLogging());
            if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
                String encryptedPassword = null;
                try {
                    encryptedPassword = AESUtil.encrypt(updatedAdmin.getPassword());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                admin.setPassword(encryptedPassword); // Šifrujemo lozinku pre čuvanja
            }
            admin.setPredefined(updatedAdmin.isPredefined());
            admin.setRole(updatedAdmin.getRole());
            admin.setEmailChecked(updatedAdmin.getEmailChecked());
            try {
                admin.setEmail(AESUtil.encrypt(updatedAdmin.getEmail()));
                admin.setPhone(AESUtil.encrypt(updatedAdmin.getPhone()));
                admin.setUsername(AESUtil.encrypt(updatedAdmin.getUsername()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return adminRepo.save(admin);
        }).orElseGet(() -> {
            updatedAdmin.setId(id);
            return adminRepo.save(updatedAdmin);
        });
    }

    public void updateUsername(Long id, String username) throws Exception {
        userRepo.updateUsernameById(id, AESUtil.encrypt(username));
    }
    
    @Override
    public Administrator registerAdmin(Administrator administrator) throws Exception {
        //String hashedPassword = passwordEncoder.encode(administrator.getPassword());
        //administrator.setPassword(hashedPassword);
        administrator.setUsername(AESUtil.encrypt(administrator.getUsername()));
        administrator.setEmail(AESUtil.encrypt(administrator.getEmail()));
        administrator.setPhone(AESUtil.encrypt(administrator.getPhone()));
        administrator.setPassword(AESUtil.encrypt(administrator.getPassword()));
        return adminRepo.save(administrator);
    }

    @Override
    public Employee registerEmployee(Employee employee) throws Exception {
        //String hashedPassword = passwordEncoder.encode(employee.getPassword());
        //employee.setPassword(hashedPassword);
        employee.setUsername(AESUtil.encrypt(employee.getUsername()));
        employee.setEmail(AESUtil.encrypt(employee.getEmail()));
        employee.setPhone(AESUtil.encrypt(employee.getPhone()));
        employee.setPassword(AESUtil.encrypt(employee.getPassword()));
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepo.findById(id).map(employee -> {
            try {
                employee.setEmail(AESUtil.encrypt(updatedEmployee.getEmail()));
                employee.setUsername(AESUtil.encrypt(updatedEmployee.getUsername()));
                employee.setPassword(AESUtil.encrypt(updatedEmployee.getPassword()));
                employee.setPhone(AESUtil.encrypt(updatedEmployee.getPhone()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            employee.setRole(updatedEmployee.getRole());
            employee.setCity(updatedEmployee.getCity());
            employee.setCountry(updatedEmployee.getCountry());
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
