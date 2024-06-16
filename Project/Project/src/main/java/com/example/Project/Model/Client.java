package com.example.Project.Model;

import com.example.Project.Enum.Face;
import com.example.Project.Enum.PackageType;
import com.example.Project.Enum.Role;
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
    private Face type;

    @NotEmpty
    private PackageType packageType;

    @NotEmpty
    private boolean tfaEnabled;

    private String secret;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    public Client(String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type, PackageType packageType, boolean tfaEnabled) {
        super(city,country,phone);
        this.clientFirmName = clientFirmName;
        this.clientSurnameFirmPIB = clientSurnameFirmPIB;
        this.clientFirmResidentialAddress = clientFirmResidentialAddress;
        this.type = type;
        this.packageType = packageType;
        this.tfaEnabled = tfaEnabled;
    }

    public Client(String username, String email, String password, Role role, String clientFirmName, String clientSurnameFirmPIB, String clientFirmResidentialAddress, String city, String country, String phone, Face type, PackageType packageType, boolean tfaEnabled) {
        super(username, email, password, role,city,country,phone, false);

        this.clientFirmName = clientFirmName;
        this.clientSurnameFirmPIB = clientSurnameFirmPIB;
        this.clientFirmResidentialAddress = clientFirmResidentialAddress;

        this.type = type;
        this.packageType = packageType;
        this.tfaEnabled = tfaEnabled;
    }

    public Client(Client client) {
        super(client.getUsername(), client.getEmail(), client.getPassword(), client.getRole(),client.getCity(),client.getCountry(),client.getPhone(), false);

        this.clientFirmName = getClientFirmName();
        this.clientSurnameFirmPIB = client.getClientSurnameFirmPIB();
        this.clientFirmResidentialAddress = client.getClientFirmResidentialAddress();

        this.type = client.getType();
        this.packageType = client.getPackageType();
        this.tfaEnabled = client.isTfaEnabled();
        this.secret = client.getSecret();
        this.company = client.getCompany();
    }


    public Client() {
    }
}
