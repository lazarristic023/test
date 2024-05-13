package com.example.Project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="clients")
@Getter
@Setter
public class Client extends User {

    @NotEmpty
    private String ClientFirmName;

    @NotEmpty
    private String ClientSurnameFirmPIB;

    @NotEmpty
    private String ClientFirmResidentialAddress;

    @NotEmpty
    private String City;

    @NotEmpty
    private String Country;

    @NotEmpty
    private String Phone;

    @NotEmpty
    private Face Type;

    public Client(String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type) {
        ClientFirmName = clientFirmName;
        ClientSurnameFirmPIB = clientSurnameFirmPIB;
        ClientFirmResidentialAddress = clientFirmResidentialAddress;
        City = city;
        Country = country;
        Phone = phone;
        Type = type;
    }

    public Client(String username, String email, String password, Role role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type) {
        super(username, email, password, role);
        ClientFirmName = clientFirmName;
        ClientSurnameFirmPIB = clientSurnameFirmPIB;
        ClientFirmResidentialAddress = clientFirmResidentialAddress;
        City = city;
        Country = country;
        Phone = phone;
        Type = type;
    }


    public Client() {
    }
}
