package com.example.Project.Repository;

import com.example.Project.Model.Commercial;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommercialRepo extends JpaRepository<Commercial,Long> {
    List<Commercial> findByClientId(long clientId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Commercial cr WHERE cr.clientId = :clientId")
    void deleteByClientId(@Param("clientId") Long clientId);
}