package com.example.Project.Repository;

import com.example.Project.Model.Client;
import com.example.Project.Model.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {

}
