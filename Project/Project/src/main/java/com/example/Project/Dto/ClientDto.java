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

    private String clientFirmName;

    private String clientSurnameFirmPIB;

    private String clientFirmResidentialAddress;

    private String city;

    private String country;

    private String phone;


    private String type;

    private String packageType;

    public ClientDto(String username, String email, String password, String role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, String type, String packageType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.clientFirmName = clientFirmName;
        this.clientSurnameFirmPIB = clientSurnameFirmPIB;
        this.clientFirmResidentialAddress = clientFirmResidentialAddress;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.type = type;
        this.packageType = packageType;
    }

    public ClientDto() {
    }
}
