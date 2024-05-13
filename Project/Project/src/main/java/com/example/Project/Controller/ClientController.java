package com.example.Project.Controller;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Dto.CommercialDto;
import com.example.Project.Mapper.ClientMapper;
import com.example.Project.Model.Client;
import com.example.Project.Model.Commercial;
import com.example.Project.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @CrossOrigin(origins = "*")
    @GetMapping("/get-by/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable long clientId){
        Client client = clientService.getById(clientId);
        ClientDto dto = clientMapper.mapToDto(client);
        return ResponseEntity.ok(dto);
    }
}
