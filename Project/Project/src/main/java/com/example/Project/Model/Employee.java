package com.example.Project.Model;

import com.example.Project.Enum.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employees")
@Getter
@Setter
public class Employee extends User{
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private String firstName;
    private String lastName;
    private boolean isFirstLogging=true;

    public Employee() {
    }

    public Employee(String username, String email, String password, Role role,Company company,String firstName,String lastName,String city,String country, String phone) {
        super(username, email, password, role,city,country,phone);
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
