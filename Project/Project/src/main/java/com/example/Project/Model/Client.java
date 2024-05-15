package com.example.Project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private String clientFirmName;

    @NotEmpty
    private String clientSurnameFirmPIB;

    @NotEmpty
    private String clientFirmResidentialAddress;

    @NotEmpty
    private String city;

    @NotEmpty
    private String country;

    @NotEmpty
    private String phone;

    @NotEmpty
    private Face type;

    @NotEmpty
    private PackageType packageType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    public Client(String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type, PackageType packageType) {
        this.clientFirmName = clientFirmName;
        this.clientSurnameFirmPIB = clientSurnameFirmPIB;
        this.clientFirmResidentialAddress = clientFirmResidentialAddress;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.type = type;
        this.packageType = packageType;
    }

    public Client(String username, String email, String password, Role role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type) {
        super(username, email, password, role);
        this.clientFirmName = clientFirmName;
        this.clientSurnameFirmPIB = clientSurnameFirmPIB;
        this.clientFirmResidentialAddress = clientFirmResidentialAddress;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.type = type;
        this.packageType = packageType;
    }


    public Client() {
    }
}
