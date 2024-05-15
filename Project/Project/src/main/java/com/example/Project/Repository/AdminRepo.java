package com.example.Project.Repository;

import com.example.Project.Model.Administrator;
import com.example.Project.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Administrator,Long> {


}
