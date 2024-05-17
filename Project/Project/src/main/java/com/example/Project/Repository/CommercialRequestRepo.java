package com.example.Project.Repository;

import com.example.Project.Model.Commercial;
import com.example.Project.Model.CommercialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialRequestRepo extends JpaRepository<CommercialRequest,Long> {
}