package com.example.Project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="companies")
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    // Jedan-na-mnoge odnos prema User
    @JsonIgnore // spriječava beskonačnu rekurziju

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;
    @JsonIgnore // spriječava beskonačnu rekurziju

    // Jedan-na-mnoge odnos prema Client
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Client> clients;

    public Company(String name, String address, List<Employee> employees, List<Client> clients) {
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.clients = clients;
    }

    public Company(){}
}
