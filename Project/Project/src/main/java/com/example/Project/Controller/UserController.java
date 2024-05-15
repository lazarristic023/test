package com.example.Project.Controller;

import com.example.Project.Dto.CompanyDto;
import com.example.Project.Model.Administrator;
import com.example.Project.Model.Company;
import com.example.Project.Model.Employee;
import com.example.Project.Model.User;
import com.example.Project.Repository.EmployeeRepo;
import com.example.Project.Service.CompanyService;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    CompanyService companyService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<User>> getAllEmployees() {
        return ResponseEntity.ok(userService.getAllEmployees());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getAdminById/{id}")
    public ResponseEntity<Optional<Administrator>> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAdminById(id));
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeyId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getEmployeeById(id));
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/editAdmin/{id}")
    public ResponseEntity<Administrator> updateAdmin(@PathVariable Long id, @RequestBody Administrator updatedAdmin) {
        Administrator admin = userService.updateAdmin(id, updatedAdmin);
        return ResponseEntity.ok(admin);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/editEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = userService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/registerAdmin")
    public ResponseEntity<Administrator> registerAdmin(@RequestBody Administrator admin) {

        return ResponseEntity.ok( userService.registerAdmin(admin));
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/registerEmployee")
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) {

        return ResponseEntity.ok( userService.registerEmployee(employee));
    }
}
