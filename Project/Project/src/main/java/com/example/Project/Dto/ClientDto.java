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

    private String clientFirmName;

    private String clientSurnameFirmPIB;

    private String clientFirmResidentialAddress;

    private String city;

    private String country;

    private String phone;


    private String type;

    private String packageType;

    private boolean tfaEnabled;

    public ClientDto(String username, String email, String password, String role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, String type, String packageType, boolean tfaEnabled) {
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
        this.tfaEnabled = tfaEnabled;
    }

    public ClientDto() {
    }
}
