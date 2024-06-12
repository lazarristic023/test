package com.example.Project.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CompanyDto {
    private String name;
    private String address;

    private List<ClientDto> clients;
    private List<EmployeeDto> employees;

    public CompanyDto(String name, String address, List<ClientDto> clients, List<EmployeeDto> employees) {
        this.name = name;
        this.address = address;
        this.clients = clients;
        this.employees = employees;
    }
    public CompanyDto(){}
}
