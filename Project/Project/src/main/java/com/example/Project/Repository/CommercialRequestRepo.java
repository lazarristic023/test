package com.example.Project.Repository;

import com.example.Project.Model.Commercial;
import com.example.Project.Model.CommercialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommercialRequestRepo extends JpaRepository<CommercialRequest,Long> {
    @Query("SELECT cr FROM CommercialRequest cr WHERE cr.clientId = :clientId AND cr.isAccepted = :isAccepted")
    CommercialRequest findByClientIdAndIsAccepted(@Param("clientId") long clientId, @Param("isAccepted") boolean isAccepted);
}
