package com.example.Project.Service;

import com.example.Project.Model.Commercial;
import com.example.Project.Repository.CommercialRepo;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CommercialService {

    @Autowired
    private CommercialRepo commercialRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommercialService.class);

    public CommercialService(CommercialRepo commercialRepository) {
        this.commercialRepository = commercialRepository;
    }

    public List<Commercial> findByClientId(long clientId) {
        try {
            List<Commercial> commercials = commercialRepository.findByClientId(clientId);
            logger.info("EventID: 4000 | Date: {} | Time: {} | Source: CommercialService | Type: INFO | Message: Found commercials by client ID | ClientId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), clientId);
            return commercials;
        } catch (Exception e) {
            logger.error("EventID: 4000 | Date: {} | Time: {} | Source: CommercialService | Type: ERROR | Message: Error finding commercials by client ID | ClientId: {} | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), clientId, e.getMessage());
            throw e;
        }
    }

    public List<Commercial> getAll() {
        try {
            List<Commercial> commercials = commercialRepository.findAll();
            logger.info("EventID: 4001 | Date: {} | Time: {} | Source: CommercialService | Type: INFO | Message: Get all commercials",
                    java.time.LocalDate.now(), java.time.LocalTime.now());
            return commercials;
        } catch (Exception e) {
            logger.error("EventID: 4001 | Date: {} | Time: {} | Source: CommercialService | Type: ERROR | Message: Error getting all commercials | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    public Commercial create(Commercial commercial) {
        try {
            Commercial createdCommercial = commercialRepository.save(commercial);
            logger.info("EventID: 4002 | Date: {} | Time: {} | Source: CommercialService | Type: INFO | Message: Created new commercial | CommercialId: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), createdCommercial.getId());
            return createdCommercial;
        } catch (Exception e) {
            logger.error("EventID: 4002 | Date: {} | Time: {} | Source: CommercialService | Type: ERROR | Message: Error creating commercial | Error: {}",
                    java.time.LocalDate.now(), java.time.LocalTime.now(), e.getMessage());
            throw e;
        }
    }

    @RateLimiter(name = "basic")
    public String clickBasicAd() {
        System.out.println("Basic Ad clicked!");
        return "Basic Ad clicked!";
    }

    @RateLimiter(name = "standard")
    public String clickStandardAd() {
        System.out.println("Standard Ad clicked!");
        return "Standard Ad clicked!";
    }

    @RateLimiter(name = "gold")
    public String clickGoldAd() {
        System.out.println("Gold Ad clicked!");
        return "Gold Ad clicked!";
    }
}
