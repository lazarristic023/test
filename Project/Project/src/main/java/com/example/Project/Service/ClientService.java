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
        String hashedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(hashedPassword);
        client.setUsername(AESUtil.encrypt(client.getUsername()));
        client.setEmail(AESUtil.encrypt(client.getEmail()));
        client.setPhone(AESUtil.encrypt(client.getPhone()));
        client.setCity(AESUtil.encrypt(client.getCity()));
        client.setCountry(AESUtil.encrypt(client.getCountry()));
        client.setClientFirmName(AESUtil.encrypt(client.getClientFirmName()));
        client.setClientSurnameFirmPIB(AESUtil.encrypt(client.getClientSurnameFirmPIB()));
        client.setClientFirmResidentialAddress(AESUtil.encrypt(client.getClientFirmResidentialAddress()));
        return clientRepository.save(client);
    }

    public Client getById(long clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        client.setUsername(AESUtil.decrypt(client.getUsername()));
        client.setEmail(AESUtil.decrypt(client.getEmail()));
        client.setPhone(AESUtil.decrypt(client.getPhone()));
        client.setCity(AESUtil.decrypt(client.getCity()));
        client.setCountry(AESUtil.decrypt(client.getCountry()));
        client.setClientFirmName(AESUtil.decrypt(client.getClientFirmName()));
        client.setClientSurnameFirmPIB(AESUtil.decrypt(client.getClientSurnameFirmPIB()));
        client.setClientFirmResidentialAddress(AESUtil.decrypt(client.getClientFirmResidentialAddress()));
        return client;
    }

    public Client getByEmail(String email) throws Exception {
        Client client = clientRepository.findByEmail(AESUtil.encrypt(email));
        client.setUsername(AESUtil.decrypt(client.getUsername()));
        client.setEmail(AESUtil.decrypt(client.getEmail()));
        client.setPhone(AESUtil.decrypt(client.getPhone()));
        client.setCity(AESUtil.decrypt(client.getCity()));
        client.setCountry(AESUtil.decrypt(client.getCountry()));
        client.setClientFirmName(AESUtil.decrypt(client.getClientFirmName()));
        client.setClientSurnameFirmPIB(AESUtil.decrypt(client.getClientSurnameFirmPIB()));
        client.setClientFirmResidentialAddress(AESUtil.decrypt(client.getClientFirmResidentialAddress()));
        return client;
    }

    public void updateClientFirmNameById(Long id, String clientFirmName) throws Exception {
        clientRepository.updateClientFirmNameById(id, AESUtil.encrypt(clientFirmName));
    }

    public void updateClientSurnameFirmPIBById(Long id, String clientSurnameFirmPIB) throws Exception {
        clientRepository.updateClientSurnameFirmPIBById(id, AESUtil.encrypt(clientSurnameFirmPIB));
    }

    public void updateClientFirmResidentialAddressById(Long id, String clientFirmResidentialAddress) throws Exception {
        clientRepository.updateClientFirmResidentialAddressById(id, AESUtil.encrypt(clientFirmResidentialAddress));
    }

    public void updateCityById(Long id, String city) throws Exception {
        clientRepository.updateCityById(id, AESUtil.encrypt(city));
    }

    public void updateCountryById(Long id, String country) throws Exception {
        clientRepository.updateCountryById(id, AESUtil.encrypt(country));
    }

    public void updatePhoneById(Long id, String phone) throws Exception {
        clientRepository.updatePhoneById(id, AESUtil.encrypt(phone));
    }
}
