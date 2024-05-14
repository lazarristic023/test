package com.example.Project.Repository;

import com.example.Project.Model.Request;
import com.example.Project.Model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {

    @Query("SELECT r FROM Request r WHERE r.username = :username")
    Request findRequestsByUsername(@Param("username") String username);


}
