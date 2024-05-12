package com.example.Project.Dto;

import com.example.Project.Model.Face;
import com.example.Project.Model.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private String username;

    private String email;

    private String password;

    private Role role;

    private String ClientFirmName;

    private String ClientSurnameFirmPIB;

    private String ClientFirmResidentialAddress;

    private String City;

    private String Country;

    private String Phone;

    private Face Type;

    public ClientDto(String username, String email, String password, Role role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        ClientFirmName = clientFirmName;
        ClientSurnameFirmPIB = clientSurnameFirmPIB;
        ClientFirmResidentialAddress = clientFirmResidentialAddress;
        City = city;
        Country = country;
        Phone = phone;
        Type = type;
    }

    public ClientDto() {
    }
}
