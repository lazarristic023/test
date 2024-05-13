package com.example.Project.Service;

import com.example.Project.Model.Client;
import com.example.Project.Repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepository;

    public ClientService(ClientRepo clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) { return clientRepository.save(client); }
}
