package com.example.Project.Service.impl;

import com.example.Project.Enum.PackageType;
import com.example.Project.Model.*;
import com.example.Project.Repository.*;
import com.example.Project.Service.CommercialRequestService;
import com.example.Project.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public User findByUsername(String name) {
        try {
            User user = userRepo.findByUsername(name);
            logger.info("EventID: 1000 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Found user by username | Username: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), name);
            return user;
        } catch (Exception e) {
            logger.error("EventID: 1000 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error finding user by username | Username: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), name, e.getMessage());
            throw e;
        }
    }

    public User findByEmail(String email) {
        try {
            User user = userRepo.findByEmail(email);
            logger.info("EventID: 1001 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Found user by email | Email: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), email);
            return user;
        } catch (Exception e) {
            logger.error("EventID: 1001 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error finding user by email | Email: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), email, e.getMessage());
            throw e;
        }
    }

    public User save(User user) {
        try {
            User savedUser = userRepo.save(user);
            logger.info("EventID: 1002 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: User saved | UserId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            logger.error("EventID: 1002 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error saving user | UserId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), user.getId(), e.getMessage());
            throw e;
        }
    }

    public User getById(Long id) {
        try {
            User user = userRepo.getById(id);
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
    public void updateEmail(Long id, String email) {
        try {
            userRepo.updateEmailById(id, email);
            logger.info("EventID: 1004 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update email | UserId: {} | NewEmail: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, email);
        } catch (Exception e) {
            logger.error("EventID: 1004 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error updating email | UserId: {} | NewEmail: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, email, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> findAll() {
        try {
            List<User> users = userRepo.findAll();
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
    public List<User> getAllClients() {
        try {
            List<User> clients = new ArrayList<>();
            for (User user : findAll()) {
                if (user.getRole().toString().equals("CLIENT")) {
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
    public List<User> getAllEmployees() {
        try {
            List<User> employees = new ArrayList<>();
            for (User user : findAll()) {
                if (Objects.equals(user.getRole().toString(), "EMPLOYEE")) {
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
            Optional<Administrator> admin = adminRepo.findById(id);
            logger.info("EventID: 1008 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Get admin by id | AdminId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return admin;
        } catch (Exception e) {
            logger.error("EventID: 1008 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error getting admin by id | AdminId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        try {
            Optional<Employee> employee = employeeRepo.findById(id);
            logger.info("EventID: 1009 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Get employee by id | EmployeeId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return employee;
        } catch (Exception e) {
            logger.error("EventID: 1009 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error getting employee by id | EmployeeId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    public Administrator updateAdmin(Long id, Administrator updatedAdmin) {
        try {
            Administrator admin = adminRepo.findById(id).map(existingAdmin -> {
                existingAdmin.setFirstName(updatedAdmin.getFirstName());
                existingAdmin.setLastName(updatedAdmin.getLastName());
                existingAdmin.setUsername(updatedAdmin.getUsername());
                existingAdmin.setEmail(updatedAdmin.getEmail());
                existingAdmin.setFirstLogging(updatedAdmin.isFirstLogging());
                if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
                    existingAdmin.setPassword(updatedAdmin.getPassword()); // Pretpostavimo da je password već hashed
                }
                existingAdmin.setPredefined(updatedAdmin.isPredefined());
                existingAdmin.setRole(updatedAdmin.getRole());
                existingAdmin.setEmailChecked(updatedAdmin.getEmailChecked());
                return adminRepo.save(existingAdmin);
            }).orElseGet(() -> {
                updatedAdmin.setId(id);
                return adminRepo.save(updatedAdmin);
            });
            logger.info("EventID: 1010 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update admin | AdminId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return admin;
        } catch (Exception e) {
            logger.error("EventID: 1010 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error updating admin | AdminId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }

    public void updateUsername(Long id, String username) {
        try {
            userRepo.updateUsernameById(id, username);
            logger.info("EventID: 1011 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update username | UserId: {} | NewUsername: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, username);
        } catch (Exception e) {
            logger.error("EventID: 1011 | Date: {} | Time: {} | Source: UserServiceImpl | Type: ERROR | Message: Error updating username | UserId: {} | NewUsername: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, username, e.getMessage());
            throw e;
        }
    }

    @Override
    public Administrator registerAdmin(Administrator administrator) {
        try {
            String hashedPassword = passwordEncoder.encode(administrator.getPassword());
            administrator.setPassword(hashedPassword);
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
    public Employee registerEmployee(Employee employee) {
        try {
            String hashedPassword = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(hashedPassword);
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
            Employee employee = employeeRepo.findById(id).map(existingEmployee -> {
                existingEmployee.setUsername(updatedEmployee.getUsername());
                existingEmployee.setEmail(updatedEmployee.getEmail());
                existingEmployee.setPassword(updatedEmployee.getPassword());
                existingEmployee.setRole(updatedEmployee.getRole());
                existingEmployee.setCity(updatedEmployee.getCity());
                existingEmployee.setCountry(updatedEmployee.getCountry());
                existingEmployee.setPhone(updatedEmployee.getPhone());
                existingEmployee.setFirstName(updatedEmployee.getFirstName());
                existingEmployee.setLastName(updatedEmployee.getLastName());
                existingEmployee.setFirstLogging(updatedEmployee.isFirstLogging());
                return employeeRepo.save(existingEmployee);
            }).orElseGet(() -> {
                updatedEmployee.setId(id);
                return employeeRepo.save(updatedEmployee);
            });
            logger.info("EventID: 1014 | Date: {} | Time: {} | Source: UserServiceImpl | Type: INFO | Message: Update employee | EmployeeId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return employee;
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
}
