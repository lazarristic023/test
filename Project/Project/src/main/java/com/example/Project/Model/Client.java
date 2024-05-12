package com.example.Project.Model;

import com.example.Project.Enum.Face;
import com.example.Project.Enum.PackageType;
import com.example.Project.Enum.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private PackageType PackageType;

    public Client(String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type, PackageType packageType) {
        ClientFirmName = clientFirmName;
        ClientSurnameFirmPIB = clientSurnameFirmPIB;
        ClientFirmResidentialAddress = clientFirmResidentialAddress;
        City = city;
        Country = country;
        Phone = phone;
        Type = type;
        PackageType = packageType;
    }

    public Client(String username, String email, String password, Role role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type, PackageType packageType) {
        super(username, email, password, role);
        ClientFirmName = clientFirmName;
        ClientSurnameFirmPIB = clientSurnameFirmPIB;
        ClientFirmResidentialAddress = clientFirmResidentialAddress;
        City = city;
        Country = country;
        Phone = phone;
        Type = type;
        PackageType = packageType;
    }


    public Client() {
    }
}
