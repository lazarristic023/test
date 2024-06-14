package com.example.Project.Repository;

import com.example.Project.Model.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommercialRepo extends JpaRepository<Commercial,Long> {
    List<Commercial> findByClientId(long clientId);
}