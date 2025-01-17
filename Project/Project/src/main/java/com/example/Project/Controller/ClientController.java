package com.example.Project.Controller;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Mapper.ClientMapper;
import com.example.Project.Model.Client;

import com.example.Project.Model.User;
import com.example.Project.Service.ClientService;
import com.example.Project.Service.TwoFactorAuthenticationService;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "= 'https://localhost:8081")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TwoFactorAuthenticationService tfaService;



    @PostMapping("/register")
    public ResponseEntity<ClientDto> registerClient(@RequestBody ClientDto clientDto) throws Exception {
        Client client = clientMapper.mapToModel(clientDto);
        if (clientDto.isTfaEnabled()) {
            client.setSecret(tfaService.generateNewSecret());
        }
        Client savedClient = clientService.save(client);
        ClientDto savedClientDto = clientMapper.mapToDto(savedClient);
        return ResponseEntity.ok(savedClientDto);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-by/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable long clientId) throws Exception {
        Client client = clientService.getById(clientId);
        ClientDto dto = clientMapper.mapToDto(client);
        return ResponseEntity.ok(dto);
    }

    @CrossOrigin(origins = "*")
    @PutMapping ("/clientFirmName/{id}/{cfn}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> updateClientFirmNameById(@PathVariable Long id, @PathVariable String cfn) throws Exception {
        clientService.updateClientFirmNameById(id, cfn);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/surnameFirmPIB/{id}/{sfp}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> updateClientSurnameFirmPIBById(@PathVariable Long id, @PathVariable String sfp) throws Exception {
        clientService.updateClientSurnameFirmPIBById(id, sfp);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/firmResidentialAddress/{id}/{fra}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> updateClientFirmResidentialAddressById(@PathVariable Long id, @PathVariable String fra) throws Exception {
        clientService.updateClientFirmResidentialAddressById(id, fra);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/city/{id}/{c}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> updateCityById(@PathVariable Long id, @PathVariable String c) throws Exception {
        clientService.updateCityById(id, c);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/country/{id}/{c}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> updateCountryById(@PathVariable Long id, @PathVariable String c) throws Exception {
        clientService.updateCountryById(id, c);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/phone/{id}/{p}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> updatePhoneById(@PathVariable Long id, @PathVariable String p) throws Exception {
        clientService.updatePhoneById(id, p);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/email/{id}/{e}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> updateEmailById(@PathVariable Long id, @PathVariable String e) throws Exception {
        userService.updateEmail(id, e);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllClients")
    public ResponseEntity<List<User>> getAllClients() throws Exception {
        return ResponseEntity.ok(userService.getAllClients());
    }
    
    @PutMapping("/username/{id}/{u}")
    public ResponseEntity<Void> updateUsernameById(@PathVariable Long id, @PathVariable String u) throws Exception {
        userService.updateUsername(id, u);
        return ResponseEntity.ok().build();
    }
}
