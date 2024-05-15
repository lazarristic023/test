package com.example.Project.Repository;

import com.example.Project.Model.Company;
import com.example.Project.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {


}
