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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'EMPLOYEE')")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    CompanyService companyService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllEmployees")
    //@PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<User>> getAllEmployees() throws Exception {
        return ResponseEntity.ok(userService.getAllEmployees());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllCompanies")
    //@PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getAdminById/{id}")
    //@PreAuthorize("hasAuthority('admin:read')")
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
    //@PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Administrator> updateAdmin(@PathVariable Long id, @RequestBody Administrator updatedAdmin) {
        Administrator admin = userService.updateAdmin(id, updatedAdmin);
        return ResponseEntity.ok(admin);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/editEmployee/{id}")
    //@PreAuthorize("hasAnyAuthority('employee:update')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = userService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/registerAdmin")
    //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Administrator> registerAdmin(@RequestBody Administrator admin) throws Exception {

        return ResponseEntity.ok( userService.registerAdmin(admin));
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/registerEmployee")
    //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) throws Exception {

        return ResponseEntity.ok( userService.registerEmployee(employee));
    }
}
