package com.example.Project.Dto;

import com.example.Project.Model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

    private String username;

    private String email;

    private String password;

    private String role;
    private Company company;
    private String firstName;
    private String lastName;

    private String city;

    private String country;

    private String phone;


    public EmployeeDto(String username, String email, String password, String role, Company company, String firstName, String lastName, String city, String country, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public EmployeeDto(){}
}
