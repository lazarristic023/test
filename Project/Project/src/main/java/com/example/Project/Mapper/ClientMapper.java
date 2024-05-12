package com.example.Project.Mapper;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Model.Client;
import com.example.Project.Model.Face;
import com.example.Project.Model.Role;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto mapToDto(Client client) {
        ClientDto clientDto = new ClientDto();
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
        return clientDto;
    }

    public Client mapToModel(ClientDto clientDto) {
        Client client = new Client();
        client.setUsername(clientDto.getUsername());
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        if(clientDto.getRole().equals("CLIENT")) client.setRole(Role.CLIENT);
        else if (clientDto.getRole().equals("EMPLOYEE")) client.setRole(Role.EMPLOYEE);
        else client.setRole(Role.ADMINISTRATOR);
        client.setClientFirmName(clientDto.getClientFirmName());
        client.setClientSurnameFirmPIB(clientDto.getClientSurnameFirmPIB());
        client.setClientFirmResidentialAddress(clientDto.getClientFirmResidentialAddress());
        client.setCity(clientDto.getCity());
        client.setCountry(clientDto.getCountry());
        client.setPhone(clientDto.getPhone());
        if(clientDto.getType().equals("PHYSICALLY")) client.setType(Face.PHYSICALLY);
        else client.setType(Face.LEGALLY);
        return client;
    }


}
