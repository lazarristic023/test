package com.example.Project.Service;

import com.example.Project.Model.CommercialRequest;
import com.example.Project.Repository.CommercialRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommercialRequestService {

    @Autowired
    private CommercialRequestRepo commercialRequestRepository;

    public CommercialRequestService(CommercialRequestRepo commercialRequestRepository) {
        this.commercialRequestRepository = commercialRequestRepository;
    }

    public CommercialRequest save(CommercialRequest commercialRequest) { return commercialRequestRepository.save(commercialRequest); }

    public List<CommercialRequest> getAll(){return commercialRequestRepository.findAll();}

    public CommercialRequest getByClient(Long id){return  commercialRequestRepository.findByClientIdAndIsAccepted(id,false);}
}
