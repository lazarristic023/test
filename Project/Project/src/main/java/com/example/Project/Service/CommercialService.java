package com.example.Project.Service;

import com.example.Project.Model.Commercial;
import com.example.Project.Repository.CommercialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommercialService {

    @Autowired
    private CommercialRepo commercialRepository;

    public CommercialService(CommercialRepo commercialRepository) {
        this.commercialRepository = commercialRepository;
    }

    public List<Commercial> findByClientId(long clientId) { return commercialRepository.findByClientId(clientId); }
}