package com.example.Project.Mapper;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto mapToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setUsername(client.getUsername());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setRole(client.getRole());
        clientDto.setClientFirmName(client.getClientFirmName());
        clientDto.setClientSurnameFirmPIB(client.getClientSurnameFirmPIB());
        clientDto.setClientFirmResidentialAddress(client.getClientFirmResidentialAddress());
        clientDto.setCity(client.getCity());
        clientDto.setCountry(client.getCountry());
        clientDto.setPhone(client.getPhone());
        clientDto.setType(client.getType());
        return clientDto;
    }

    public Client mapToModel(ClientDto clientDto) {
        Client client = new Client();
        client.setUsername(clientDto.getUsername());
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        client.setRole(clientDto.getRole());
        client.setClientFirmName(clientDto.getClientFirmName());
        client.setClientSurnameFirmPIB(clientDto.getClientSurnameFirmPIB());
        client.setClientFirmResidentialAddress(clientDto.getClientFirmResidentialAddress());
        client.setCity(clientDto.getCity());
        client.setCountry(clientDto.getCountry());
        client.setPhone(clientDto.getPhone());
        client.setType(clientDto.getType());
        return client;
    }

    
}
