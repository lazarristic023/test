package com.example.Project.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private String username;

    private String email;

    private String password;

    private String role;

    private String ClientFirmName;

    private String ClientSurnameFirmPIB;

    private String ClientFirmResidentialAddress;

    private String City;

    private String Country;

    private String Phone;

    private String Type;

    private String PackageType;

    public ClientDto(String username, String email, String password, String role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, String type, String packageType) {
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
        PackageType = packageType;
    }

    public ClientDto() {
    }
}
