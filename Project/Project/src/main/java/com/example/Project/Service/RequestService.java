package com.example.Project.Service;

import com.example.Project.Model.Request;
import com.example.Project.Repository.RequestRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {


    private static final Logger logger = LoggerFactory.getLogger(CommercialRequestService.class);

    @Autowired
    public RequestRepo requestRepo;

    public List<Request> getAllRequests() {
        logger.info("Fetching all requests");

        try {
            List<Request> requests = requestRepo.findAll();
            logger.info("Successfully fetched all requests, count: {}", requests.size());
            return requests;
        } catch (Exception e) {
            logger.error("Error fetching all requests: {}", e.getMessage());
            throw e;
        }
    }

    public Request create(Request newRequest) {
        logger.info("Creating new request with ID: {}", newRequest.getId());

        try {
            Request request = requestRepo.save(newRequest);
            logger.info("Successfully created request with ID: {}", request.getId());
            return request;
        } catch (Exception e) {
            logger.error("Error creating request: {}", e.getMessage());
            throw e;
        }
    }

    public Request getByClientId(String username) {
        logger.info("Fetching request for username: {}", username);

        try {
            Request request = requestRepo.findRequestsByUsername(username);
            if (request != null) {
                logger.info("Successfully fetched request for username: {}", username);
            } else {
                logger.warn("No request found for username: {}", username);
            }
            return request;
        } catch (Exception e) {
            logger.error("Error fetching request for username: {}", e.getMessage());
            throw e;
        }
    }
}
