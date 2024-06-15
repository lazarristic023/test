package com.example.Project.Config;

import io.github.resilience4j.ratelimiter.event.RateLimiterOnFailureEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

import java.time.Duration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterRegistry registry = RateLimiterRegistry.ofDefaults();


        // Konfiguracija za osnovni paket
        registry.rateLimiter("basic",
                io.github.resilience4j.ratelimiter.RateLimiterConfig.custom()
                        .timeoutDuration(Duration.ofMillis(0))
                        .limitForPeriod(10)
                        .limitRefreshPeriod(Duration.ofMinutes(1))
                        .build());

        // Konfiguracija za standardni paket
        registry.rateLimiter("standard",
                io.github.resilience4j.ratelimiter.RateLimiterConfig.custom()
                        .timeoutDuration(Duration.ofMillis(0))
                        .limitForPeriod(100)
                        .limitRefreshPeriod(Duration.ofMinutes(1))
                        .build());

        // Konfiguracija za zlatni paket
        registry.rateLimiter("gold",
                io.github.resilience4j.ratelimiter.RateLimiterConfig.custom()
                        .timeoutDuration(Duration.ofMillis(0))
                        .limitForPeriod(10000)
                        .limitRefreshPeriod(Duration.ofMinutes(1))
                        .build());

        registry.getAllRateLimiters().forEach(rateLimiter -> {
            rateLimiter.getEventPublisher().onFailure(event -> handleRateLimiterEvent(event));
        });

        return registry;
    }

    private void handleRateLimiterEvent(RateLimiterOnFailureEvent event) {
        System.out.println("RateLimiter exceeded: " + event.getRateLimiterName() + " - " + event.toString());
    }
}