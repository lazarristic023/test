package com.example.Project.Service.impl;

import com.example.Project.Enum.PackageType;
import com.example.Project.Model.*;
import com.example.Project.Repository.*;
import com.example.Project.Service.CommercialRequestService;

import com.example.Project.Dto.ChangePasswordRequest;
import com.example.Project.Dto.ResetPasswordRequest;
import com.example.Project.Model.Administrator;
import com.example.Project.Model.Employee;
import com.example.Project.Model.User;
import com.example.Project.Repository.AdminRepo;
import com.example.Project.Repository.EmployeeRepo;
import com.example.Project.Repository.UserRepo;
import com.example.Project.Service.UserService;
import com.example.Project.Utilities.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private CommercialRepo commercialRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private CommercialRequestRepo commercialRequestRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String name) throws Exception {
        try {
            User user = userRepo.findByUsername(AESUtil.encrypt(name));
            if (user != null) {
                user.setUsername(AESUtil.decrypt(user.getUsername()));
                user.setEmail(AESUtil.decrypt(user.getEmail()));
                user.setPhone(AESUtil.decrypt(user.getPhone()));
                user.setCity(AESUtil.decrypt(user.getCity()));
                user.setCountry(AESUtil.decrypt(user.getCountry()));
            }
            logger.info("EventID: 1000 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Found user by username | Username: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), name);
            return user;
        } catch (Exception e) {
            logger.error("EventID: 1000 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error finding user by username | Username: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), name, e.getMessage());
            throw e;
        }
    }

    public User findByEmail(String email) throws Exception {
        try {
            User user = userRepo.findByEmail(AESUtil.encrypt(email));
            if (user != null) {
                user.setUsername(AESUtil.decrypt(user.getUsername()));
                user.setEmail(AESUtil.decrypt(user.getEmail()));
                user.setPhone(AESUtil.decrypt(user.getPhone()));
                user.setCity(AESUtil.decrypt(user.getCity()));
                user.setCountry(AESUtil.decrypt(user.getCountry()));
            }
            logger.info("EventID: 1001 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Found user by email | Email: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), email);
            return user;
        } catch (Exception e) {
            logger.error("EventID: 1001 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error finding user by email | Email: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), email, e.getMessage());
            throw e;
        }
    }

    public User save(User user) throws Exception {
        try {
            user.setUsername(AESUtil.encrypt(user.getUsername()));
            user.setEmail(AESUtil.encrypt(user.getEmail()));
            user.setPhone(AESUtil.encrypt(user.getPhone()));
            user.setPassword(AESUtil.encrypt(user.getPassword()));
            user.setCity(AESUtil.encrypt(user.getCity()));
            user.setCountry(AESUtil.encrypt(user.getCountry()));
            User savedUser = userRepo.save(user);
            logger.info("EventID: 1002 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: User saved | UserId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), savedUser.getId());
            return user;
        } catch (Exception e) {
            logger.error("EventID: 1002 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error saving user | UserId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), user.getId(), e.getMessage());
            throw e;
        }
    }

    public User getById(Long id) throws Exception {
        try {
            User user = userRepo.getById(id);
            if (user != null) {
                user.setUsername(AESUtil.decrypt(user.getUsername()));
                user.setEmail(AESUtil.decrypt(user.getEmail()));
                user.setPhone(AESUtil.decrypt(user.getPhone()));
                user.setCity(AESUtil.decrypt(user.getCity()));
                user.setCountry(AESUtil.decrypt(user.getCountry()));
            }
            logger.info("EventID: 1003 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Get user by id | UserId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return user;
        } catch (Exception e) {
            logger.error("EventID: 1003 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error getting user by id | UserId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateEmail(Long id, String email) throws Exception {
        try {
            userRepo.updateEmailById(id, AESUtil.encrypt(email));
            logger.info("EventID: 1004 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update email | UserId: {} | NewEmail: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, email);
        } catch (Exception e) {
            logger.error("EventID: 1004 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error updating email | UserId: {} | NewEmail: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, email, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try {
            List<User> users = userRepo.findAll();
            for (User user : users) {
                user.setUsername(AESUtil.decrypt(user.getUsername()));
                user.setEmail(AESUtil.decrypt(user.getEmail()));
                user.setPhone(AESUtil.decrypt(user.getPhone()));
                user.setCity(AESUtil.decrypt(user.getCity()));
                user.setCountry(AESUtil.decrypt(user.getCountry()));
            }
            logger.info("EventID: 1005 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Find all users | UserCount: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), users.size());
            return users;
        } catch (Exception e) {
            logger.error("EventID: 1005 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error finding all users | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getAllClients() throws Exception {
        try {
            List<User> clients = new ArrayList<>();
            List<User> allUsers = findAll();
            for (User user : allUsers) {
                if ("CLIENT".equals(user.getRole().toString())) {
                    user.setUsername(AESUtil.decrypt(user.getUsername()));
                    user.setEmail(AESUtil.decrypt(user.getEmail()));
                    user.setPhone(AESUtil.decrypt(user.getPhone()));
                    user.setCity(AESUtil.decrypt(user.getCity()));
                    user.setCountry(AESUtil.decrypt(user.getCountry()));
                    clients.add(user);
                }
            }
            logger.info("EventID: 1006 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Get all clients | ClientCount: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), clients.size());
            return clients;
        } catch (Exception e) {
            logger.error("EventID: 1006 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error getting all clients | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getAllEmployees() throws Exception {
        try {
            List<User> employees = new ArrayList<>();
            for (User user : findAll()) {
                if (Objects.equals(user.getRole().toString(), "EMPLOYEE")) {
                    user.setUsername(AESUtil.decrypt(user.getUsername()));
                    user.setEmail(AESUtil.decrypt(user.getEmail()));
                    user.setPhone(AESUtil.decrypt(user.getPhone()));
                    user.setCity(AESUtil.decrypt(user.getCity()));
                    user.setCountry(AESUtil.decrypt(user.getCountry()));
                    employees.add(user);
                }
            }
            logger.info("EventID: 1007 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Get all employees | EmployeeCount: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), employees.size());
            return employees;
        } catch (Exception e) {
            logger.error("EventID: 1007 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error getting all employees | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Administrator> getAdminById(Long id) {
        try {
            Optional<Administrator> optionalAdmin = adminRepo.findById(id);
            optionalAdmin.ifPresent(admin -> {
                try {
                    admin.setUsername(AESUtil.decrypt(admin.getUsername()));
                    admin.setEmail(AESUtil.decrypt(admin.getEmail()));
                    admin.setPhone(AESUtil.decrypt(admin.getPhone()));
                    admin.setCity(AESUtil.decrypt(admin.getCity()));
                    admin.setCountry(AESUtil.decrypt(admin.getCountry()));
                    admin.setLastName(AESUtil.decrypt(admin.getLastName()));
                    admin.setFirstName(AESUtil.decrypt(admin.getFirstName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            logger.info("EventID: 1008 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Get admin by id | AdminId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return optionalAdmin;
        } catch (Exception e) {
            logger.error("EventID: 1008 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error getting admin by id | AdminId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }


    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        try {
            Optional<Employee> optionalEmployee = employeeRepo.findById(id);
            optionalEmployee.ifPresent(employee -> {
                try {
                    employee.setUsername(AESUtil.decrypt(employee.getUsername()));
                    employee.setEmail(AESUtil.decrypt(employee.getEmail()));
                    employee.setPhone(AESUtil.decrypt(employee.getPhone()));
                    employee.setCity(AESUtil.decrypt(employee.getCity()));
                    employee.setCountry(AESUtil.decrypt(employee.getCountry()));
                    employee.setLastName(AESUtil.decrypt(employee.getLastName()));
                    employee.setFirstName(AESUtil.decrypt(employee.getFirstName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            logger.info("EventID: 1009 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Get employee by id | EmployeeId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return optionalEmployee;
        } catch (Exception e) {
            logger.error("EventID: 1009 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error getting employee by id | EmployeeId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    public Administrator updateAdmin(Long id, Administrator updatedAdmin) {
        try {
            Administrator a = adminRepo.findById(id).map(admin -> {
                admin.setFirstLogging(updatedAdmin.isFirstLogging());
                if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
                    admin.setPassword(updatedAdmin.getPassword());
                }
                admin.setPredefined(updatedAdmin.isPredefined());
                admin.setRole(updatedAdmin.getRole());
                admin.setEmailChecked(updatedAdmin.getEmailChecked());
                try {
                    admin.setEmail(AESUtil.encrypt(updatedAdmin.getEmail()));
                    admin.setPhone(AESUtil.encrypt(updatedAdmin.getPhone()));
                    admin.setUsername(AESUtil.encrypt(updatedAdmin.getUsername()));
                    admin.setCity(AESUtil.encrypt(updatedAdmin.getCity()));
                    admin.setCountry(AESUtil.encrypt(updatedAdmin.getCountry()));
                    admin.setFirstName(AESUtil.encrypt(updatedAdmin.getFirstName()));
                    admin.setLastName(AESUtil.encrypt(updatedAdmin.getLastName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return adminRepo.save(admin);
            }).orElseGet(() -> {
                updatedAdmin.setId(id);
                return adminRepo.save(updatedAdmin);
            });
            logger.info("EventID: 1010 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update admin | AdminId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return a;
        } catch (Exception e) {
            logger.error("EventID: 1010 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error updating admin | AdminId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    public void updateUsername(Long id, String username) throws Exception {
        try {
            userRepo.updateUsernameById(id, AESUtil.encrypt(username));
            logger.info("EventID: 1011 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update username | UserId: {} | NewUsername: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, username);
        } catch (Exception e) {
            logger.error("EventID: 1011 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error updating username | UserId: {} | NewUsername: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, username, e.getMessage());
            throw e;
        }
    }

    @Override
    public Administrator registerAdmin(Administrator administrator) throws Exception {
        try {
            String hashedPassword = passwordEncoder.encode(administrator.getPassword());
            administrator.setPassword(hashedPassword);
            administrator.setUsername(AESUtil.encrypt(administrator.getUsername()));
            administrator.setEmail(AESUtil.encrypt(administrator.getEmail()));
            administrator.setPhone(AESUtil.encrypt(administrator.getPhone()));
            administrator.setCity(AESUtil.encrypt(administrator.getCity()));
            administrator.setCountry(AESUtil.encrypt(administrator.getCountry()));
            administrator.setFirstName(AESUtil.encrypt(administrator.getFirstName()));
            administrator.setLastName(AESUtil.encrypt(administrator.getLastName()));
            Administrator savedAdmin = adminRepo.save(administrator);
            logger.info("EventID: 1012 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Register admin | AdminUsername: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), administrator.getUsername());
            return savedAdmin;
        } catch (Exception e) {
            logger.error("EventID: 1012 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error registering admin | AdminUsername: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), administrator.getUsername(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Employee registerEmployee(Employee employee) throws Exception {
        try {
            String hashedPassword = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(hashedPassword);
            employee.setUsername(AESUtil.encrypt(employee.getUsername()));
            employee.setEmail(AESUtil.encrypt(employee.getEmail()));
            employee.setPhone(AESUtil.encrypt(employee.getPhone()));
            employee.setCity(AESUtil.encrypt(employee.getCity()));
            employee.setCountry(AESUtil.encrypt(employee.getCountry()));
            employee.setFirstName(AESUtil.encrypt(employee.getFirstName()));
            employee.setLastName(AESUtil.encrypt(employee.getLastName()));
            Employee savedEmployee = employeeRepo.save(employee);
            logger.info("EventID: 1013 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Register employee | EmployeeUsername: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), employee.getUsername());
            return savedEmployee;
        } catch (Exception e) {
            logger.error("EventID: 1013 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error registering employee | EmployeeUsername: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), employee.getUsername(), e.getMessage());
            throw e;
        }
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        try {
            Employee e = employeeRepo.findById(id).map(employee -> {
                try {
                    employee.setEmail(AESUtil.encrypt(updatedEmployee.getEmail()));
                    employee.setUsername(AESUtil.encrypt(updatedEmployee.getUsername()));
                    employee.setPhone(AESUtil.encrypt(updatedEmployee.getPhone()));
                    employee.setCity(AESUtil.encrypt(updatedEmployee.getCity()));
                    employee.setCountry(AESUtil.encrypt(updatedEmployee.getCountry()));
                    employee.setFirstName(AESUtil.encrypt(updatedEmployee.getFirstName()));
                    employee.setLastName(AESUtil.encrypt(updatedEmployee.getLastName()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                employee.setPassword(updatedEmployee.getPassword());
                employee.setRole(updatedEmployee.getRole());
                employee.setFirstLogging(updatedEmployee.isFirstLogging());
                return employeeRepo.save(employee);
            }).orElseGet(() -> {
                updatedEmployee.setId(id);
                return employeeRepo.save(updatedEmployee);
            });
            logger.info("EventID: 1014 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update employee | EmployeeId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return e;
        } catch (Exception e) {
            logger.error("EventID: 1014 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error updating employee | EmployeeId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    public int DeleteClient(Long id) {
        try {
            Optional<Client> client = clientRepo.findById(id);
            if (client.isPresent() && client.get().getPackageType().equals(PackageType.GOLD)) {
                clientRepo.deleteById(id);
                userRepo.deleteById(id);
                deleteAllComercialRequest(id);
                deleteAllCommercials(id);
                logger.info("EventID: 1015 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Deleted client | ClientId: {}",
                        java.time.LocalDate.now(), java.time.LocalTime.now(), id);
                return 1;
            } else {
                logger.warn("EventID: 1015 | Date: {} | Time: {} | Source: UserServiceImpl | Type: WARN | Message: Client not deleted - not GOLD package | ClientId: {}",
                        java.time.LocalDate.now(), java.time.LocalTime.now(), id);
                return 0;
            }
        } catch (Exception e) {
            logger.error("EventID: 1015 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error deleting client | ClientId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    public void deleteAllComercialRequest(Long id) {
        try {
            commercialRequestRepo.deleteByClientId(id);
            logger.info("EventID: 1016 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Deleting all commercial requests | ClientId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
        } catch (Exception e) {
            logger.error("EventID: 1016 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error deleting all commercial requests | ClientId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    public void deleteAllCommercials(Long id) {
        try {
            commercialRepo.deleteByClientId(id);
            logger.info("EventID: 1017 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Deleting all commercials | ClientId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
        } catch (Exception e) {
            logger.error("EventID: 1017 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error deleting all commercials | ClientId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {

        User user = userRepo.findById(request.getUserId()).orElseThrow();
        if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }

        if(!request.getNewPassword().equals(request.getConfirmNewPassword())){
            throw new IllegalArgumentException("Passwords are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepo.save(user);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request){

        if(!request.getNewPassword().equals(request.getConfirmNewPassword())){
            throw new IllegalArgumentException("Passwords are not the same");
        }

        User user = userRepo.findByEmail(request.getEmail());
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);

    }
}
