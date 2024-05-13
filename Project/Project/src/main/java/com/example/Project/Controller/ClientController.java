package com.example.Project.Controller;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Dto.UserDto;
import com.example.Project.Mapper.ClientMapper;
import com.example.Project.Model.Client;
import com.example.Project.Model.Role;
import com.example.Project.Model.User;
import com.example.Project.Model.UserTokenState;
import com.example.Project.Service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;


    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<ClientDto> registerClient(@RequestBody ClientDto clientDto) {
        Client client = clientMapper.mapToModel(clientDto);
        Client savedClient = clientService.save(client);
        ClientDto savedClientDto = clientMapper.mapToDto(savedClient);
        return ResponseEntity.ok(savedClientDto);
    }
}
