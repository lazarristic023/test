package com.example.Project.Service;

import com.example.Project.Model.Client;
import com.example.Project.Repository.ClientRepo;
import com.example.Project.Utilities.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ClientService(ClientRepo clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public Client save(Client client) throws Exception {
        //String hashedPassword = passwordEncoder.encode(client.getPassword());
        //client.setPassword(hashedPassword);
        client.setUsername(AESUtil.encrypt(client.getUsername()));
        client.setEmail(AESUtil.encrypt(client.getEmail()));
        client.setPhone(AESUtil.encrypt(client.getPhone()));
        client.setPassword(AESUtil.encrypt(client.getPassword()));
        return clientRepository.save(client);
    }

    public Client getById(long clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        client.setUsername(AESUtil.decrypt(client.getUsername()));
        client.setEmail(AESUtil.decrypt(client.getEmail()));
        client.setPhone(AESUtil.decrypt(client.getPhone()));
        return client;
    }

    public Client getByEmail(String email) throws Exception {
        Client client = clientRepository.findByEmail(AESUtil.encrypt(email));
        client.setUsername(AESUtil.decrypt(client.getUsername()));
        client.setEmail(AESUtil.decrypt(client.getEmail()));
        client.setPhone(AESUtil.decrypt(client.getPhone()));
        return client;
    }

    public void updateClientFirmNameById(Long id, String clientFirmName) {
        clientRepository.updateClientFirmNameById(id, clientFirmName);
    }

    public void updateClientSurnameFirmPIBById(Long id, String clientSurnameFirmPIB) {
        clientRepository.updateClientSurnameFirmPIBById(id, clientSurnameFirmPIB);
    }

    public void updateClientFirmResidentialAddressById(Long id, String clientFirmResidentialAddress) {
        clientRepository.updateClientFirmResidentialAddressById(id, clientFirmResidentialAddress);
    }

    public void updateCityById(Long id, String city) {
        clientRepository.updateCityById(id, city);
    }

    public void updateCountryById(Long id, String country) {
        clientRepository.updateCountryById(id, country);
    }

    public void updatePhoneById(Long id, String phone) throws Exception {
        clientRepository.updatePhoneById(id, AESUtil.encrypt(phone));
    }
}
