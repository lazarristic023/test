package com.example.Project.Service;

import com.example.Project.Model.Commercial;
import com.example.Project.Repository.CommercialRepo;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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

    public List<Commercial> getAll(){return commercialRepository.findAll();}

    public Commercial create(Commercial commercial){return commercialRepository.save(commercial);}


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
