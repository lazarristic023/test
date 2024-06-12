package com.example.Project.Mapper;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Enum.PackageType;
import com.example.Project.Model.Client;

import com.example.Project.Enum.Face;
import com.example.Project.Enum.Role;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto mapToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setTfaEnabled(clientDto.isTfaEnabled());
        clientDto.setUsername(client.getUsername());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setRole(client.getRole().toString());
        clientDto.setClientFirmName(client.getClientFirmName());
        clientDto.setClientSurnameFirmPIB(client.getClientSurnameFirmPIB());
        clientDto.setClientFirmResidentialAddress(client.getClientFirmResidentialAddress());
        clientDto.setCity(client.getCity());
        clientDto.setCountry(client.getCountry());
        clientDto.setPhone(client.getPhone());
        clientDto.setType(client.getType().toString());

        clientDto.setPackageType(client.getPackageType().toString());
        return clientDto;
    }

    public Client mapToModel(ClientDto clientDto) {
        Client client = new Client();
        client.setTfaEnabled(clientDto.isTfaEnabled());
        client.setUsername(clientDto.getUsername());
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        client.setRole(Role.CLIENT);
        client.setClientFirmName(clientDto.getClientFirmName());
        client.setClientSurnameFirmPIB(clientDto.getClientSurnameFirmPIB());
        client.setClientFirmResidentialAddress(clientDto.getClientFirmResidentialAddress());
        client.setCity(clientDto.getCity());
        client.setCountry(clientDto.getCountry());
        client.setPhone(clientDto.getPhone());
        if(clientDto.getType().equals("PHYSICALLY")) client.setType(Face.PHYSICALLY);
        else client.setType(Face.LEGALLY);

        if(clientDto.getPackageType().equals("BASIC")) client.setPackageType(PackageType.BASIC);
        else if (clientDto.getPackageType().equals("STANDARD")) client.setPackageType(PackageType.STANDARD);
        else client.setPackageType(PackageType.GOLD);
        return client;
    }

    
}
