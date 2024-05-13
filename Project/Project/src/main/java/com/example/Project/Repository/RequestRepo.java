package com.example.Project.Repository;

import com.example.Project.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {

    @Query("SELECT r FROM Request r WHERE r.clientId = :clientId")
    Request findRequestsByClientId(@Param("clientId") Long clientId);


}
