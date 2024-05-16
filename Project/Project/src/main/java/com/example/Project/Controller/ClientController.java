package com.example.Project.Controller;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Mapper.ClientMapper;
import com.example.Project.Model.Client;

import com.example.Project.Model.User;
import com.example.Project.Service.ClientService;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private UserService userService;



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

    @CrossOrigin(origins = "*")
    @PutMapping ("/clientFirmName/{id}/{cfn}")
    public ResponseEntity<Void> updateClientFirmNameById(@PathVariable Long id, @PathVariable String cfn) {
        clientService.updateClientFirmNameById(id, cfn);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/surnameFirmPIB/{id}/{sfp}")
    public ResponseEntity<Void> updateClientSurnameFirmPIBById(@PathVariable Long id, @PathVariable String sfp) {
        clientService.updateClientSurnameFirmPIBById(id, sfp);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/firmResidentialAddress/{id}/{fra}")
    public ResponseEntity<Void> updateClientFirmResidentialAddressById(@PathVariable Long id, @PathVariable String fra) {
        clientService.updateClientFirmResidentialAddressById(id, fra);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/city/{id}/{c}")
    public ResponseEntity<Void> updateCityById(@PathVariable Long id, @PathVariable String c) {
        clientService.updateCityById(id, c);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/country/{id}/{c}")
    public ResponseEntity<Void> updateCountryById(@PathVariable Long id, @PathVariable String c) {
        clientService.updateCountryById(id, c);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/phone/{id}/{p}")
    public ResponseEntity<Void> updatePhoneById(@PathVariable Long id, @PathVariable String p) {
        clientService.updatePhoneById(id, p);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/email/{id}/{e}")
    public ResponseEntity<Void> updateEmailById(@PathVariable Long id, @PathVariable String e) {
        userService.updateEmail(id, e);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllClients")
    public ResponseEntity<List<User>> getAllClients() {
        return ResponseEntity.ok(userService.getAllClients());
    }
    
    @PutMapping("/username/{id}/{u}")
    public ResponseEntity<Void> updateUsernameById(@PathVariable Long id, @PathVariable String u) {
        userService.updateUsername(id, u);
        return ResponseEntity.ok().build();
    }
}
