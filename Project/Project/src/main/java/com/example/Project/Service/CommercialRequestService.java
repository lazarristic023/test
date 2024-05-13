package com.example.Project.Service;

import com.example.Project.Model.CommercialRequest;
import com.example.Project.Repository.CommercialRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommercialRequestService {

    @Autowired
    private CommercialRequestRepo commercialRequestRepository;

    public CommercialRequestService(CommercialRequestRepo commercialRequestRepository) {
        this.commercialRequestRepository = commercialRequestRepository;
    }

    public CommercialRequest save(CommercialRequest commercialRequest) { return commercialRequestRepository.save(commercialRequest); }
}
