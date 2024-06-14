package com.example.Project.Service;

import com.example.Project.Model.CommercialRequest;
import com.example.Project.Repository.CommercialRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CommercialRequestService {

    @Autowired
    private CommercialRequestRepo commercialRequestRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommercialRequestService.class);

    public CommercialRequestService(CommercialRequestRepo commercialRequestRepository) {
        this.commercialRequestRepository = commercialRequestRepository;
    }

    public CommercialRequest save(CommercialRequest commercialRequest) {
        try {
            CommercialRequest savedRequest = commercialRequestRepository.save(commercialRequest);
            logger.info("EventID: 3000 | Date: {} | Time: {} | Source: CommercialRequestService | Type: INFO | Message: Commercial request saved | RequestId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), savedRequest.getId());
            return savedRequest;
        } catch (Exception e) {
            logger.error("EventID: 3000 | Date: {} | Time: {} | Source: CommercialRequestService | Type: ERROR | Message: Error saving commercial request | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    public List<CommercialRequest> getAll() {
        try {
            List<CommercialRequest> requests = commercialRequestRepository.findAll();
            logger.info("EventID: 3001 | Date: {} | Time: {} | Source: CommercialRequestService | Type: INFO | Message: Get all commercial requests",
                    java.time.LocalDate.now(), java.time.LocalTime.now());
            return requests;
        } catch (Exception e) {
            logger.error("EventID: 3001 | Date: {} | Time: {} | Source: CommercialRequestService | Type: ERROR | Message: Error getting all commercial requests | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    public CommercialRequest getByClient(Long id) {
        try {
            CommercialRequest request = commercialRequestRepository.findByClientIdAndIsAccepted(id, false);
            logger.info("EventID: 3002 | Date: {} | Time: {} | Source: CommercialRequestService | Type: INFO | Message: Get commercial request by client | ClientId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id);
            return request;
        } catch (Exception e) {
            logger.error("EventID: 3002 | Date: {} | Time: {} | Source: CommercialRequestService | Type: ERROR | Message: Error getting commercial request by client | ClientId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), id, e.getMessage());
            throw e;
        }
    }
}
