package com.example.Project.Repository;

import com.example.Project.Model.EmailToken;
import com.example.Project.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTokenRepo extends JpaRepository<EmailToken,Long> {

    @Query("SELECT r FROM EmailToken r WHERE r.clientId = :clientId")
    EmailToken findTokenByClientId(@Param("clientId") Long clientId);
}
