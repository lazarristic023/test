package com.example.Project.Service;

import com.example.Project.Model.Client;
import com.example.Project.Repository.ClientRepo;
import com.example.Project.Utilities.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    public ClientService(ClientRepo clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client save(Client client) {
        try {
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
            Client savedClient = clientRepository.save(client);
            logger.info("EventID: 2000 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Client saved | ClientId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), savedClient.getId());
            return savedClient;
        } catch (Exception e) {
            logger.error("EventID: 2000 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error saving client | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    public Client getById(long clientId) {
        try {
            Client client = clientRepository.findById(clientId).orElse(null);
            client.setUsername(AESUtil.decrypt(client.getUsername()));
            client.setEmail(AESUtil.decrypt(client.getEmail()));
            client.setPhone(AESUtil.decrypt(client.getPhone()));
            client.setCity(AESUtil.decrypt(client.getCity()));
            client.setCountry(AESUtil.decrypt(client.getCountry()));
            client.setClientFirmName(AESUtil.decrypt(client.getClientFirmName()));
            client.setClientSurnameFirmPIB(AESUtil.decrypt(client.getClientSurnameFirmPIB()));
            client.setClientFirmResidentialAddress(AESUtil.decrypt(client.getClientFirmResidentialAddress()));
            logger.info("EventID: 2001 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Get client by id | ClientId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), clientId);
            return client;
        } catch (Exception e) {
            logger.error("EventID: 2001 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error getting client by id | ClientId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), clientId, e.getMessage());
            throw e;
        }
    }

    public Client getByEmail(String email) {
        try {
            Client client = clientRepository.findByEmail(AESUtil.encrypt(email));
            client.setUsername(AESUtil.decrypt(client.getUsername()));
            client.setEmail(AESUtil.decrypt(client.getEmail()));
            client.setPhone(AESUtil.decrypt(client.getPhone()));
            client.setCity(AESUtil.decrypt(client.getCity()));
            client.setCountry(AESUtil.decrypt(client.getCountry()));
            client.setClientFirmName(AESUtil.decrypt(client.getClientFirmName()));
            client.setClientSurnameFirmPIB(AESUtil.decrypt(client.getClientSurnameFirmPIB()));
            client.setClientFirmResidentialAddress(AESUtil.decrypt(client.getClientFirmResidentialAddress()));
            logger.info("EventID: 2002 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Get client by email | Email: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), email);
            return client;
        } catch (Exception e) {
            logger.error("EventID: 2002 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error getting client by email | Email: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), email, e.getMessage());
            throw e;
        }
    }

    public void updateClientFirmNameById(Long id, String clientFirmName) {
        try {
            clientRepository.updateClientFirmNameById(id, AESUtil.encrypt(clientFirmName));
            logger.info("EventID: 2003 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Update client firm name | ClientId: {} | NewFirmName: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, clientFirmName);
        } catch (Exception e) {
            logger.error("EventID: 2003 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error updating client firm name | ClientId: {} | NewFirmName: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, clientFirmName, e.getMessage());
            throw e;
        }
    }

    public void updateClientSurnameFirmPIBById(Long id, String clientSurnameFirmPIB) {
        try {
            clientRepository.updateClientSurnameFirmPIBById(id, AESUtil.encrypt(clientSurnameFirmPIB));
            logger.info("EventID: 2004 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Update client surname firm PIB | ClientId: {} | NewSurnameFirmPIB: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, clientSurnameFirmPIB);
        } catch (Exception e) {
            logger.error("EventID: 2004 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error updating client surname firm PIB | ClientId: {} | NewSurnameFirmPIB: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, clientSurnameFirmPIB, e.getMessage());
            throw e;
        }
    }

    public void updateClientFirmResidentialAddressById(Long id, String clientFirmResidentialAddress) {
        try {
            clientRepository.updateClientFirmResidentialAddressById(id, AESUtil.encrypt(clientFirmResidentialAddress));
            logger.info("EventID: 2005 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Update client firm residential address | ClientId: {} | NewFirmResidentialAddress: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, clientFirmResidentialAddress);
        } catch (Exception e) {
            logger.error("EventID: 2005 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error updating client firm residential address | ClientId: {} | NewFirmResidentialAddress: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, clientFirmResidentialAddress, e.getMessage());
            throw e;
        }
    }

    public void updateCityById(Long id, String city) {
        try {
            clientRepository.updateCityById(id, AESUtil.encrypt(city));
            logger.info("EventID: 2006 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Update city | ClientId: {} | NewCity: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, city);
        } catch (Exception e) {
            logger.error("EventID: 2006 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error updating city | ClientId: {} | NewCity: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, city, e.getMessage());
            throw e;
        }
    }

    public void updateCountryById(Long id, String country) {
        try {
            clientRepository.updateCountryById(id, AESUtil.encrypt(country));
            logger.info("EventID: 2007 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Update country | ClientId: {} | NewCountry: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, country);
        } catch (Exception e) {
            logger.error("EventID: 2007 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error updating country | ClientId: {} | NewCountry: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, country, e.getMessage());
            throw e;
        }
    }

    public void updatePhoneById(Long id, String phone) {
        try {
            clientRepository.updatePhoneById(id, AESUtil.encrypt(phone));
            logger.info("EventID: 2008 | Date: {} | Time: {} | Source: ClientService | Type: INFO | Message: Update phone | ClientId: {} | NewPhone: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, phone);
        } catch (Exception e) {
            logger.error("EventID: 2008 | Date: {} | Time: {} | Source: ClientService | Type: ERROR | Message: Error updating phone | ClientId: {} | NewPhone: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, phone, e.getMessage());
            throw e;
        }
    }
}
