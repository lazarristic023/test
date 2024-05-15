package com.example.Project.Controller;

import com.example.Project.Model.Administrator;
import com.example.Project.Model.Company;
import com.example.Project.Model.User;
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
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getAdminById/{id}")
    public ResponseEntity<Optional<Administrator>> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAdminById(id));
    }

    @PutMapping("/editAdmin/{id}")
    public ResponseEntity<Administrator> updateAdmin(@PathVariable Long id, @RequestBody Administrator updatedAdmin) {
        Administrator admin = userService.updateAdmin(id, updatedAdmin);
        return ResponseEntity.ok(admin);
    }
}
