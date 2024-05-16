package com.example.Project.Repository;

import com.example.Project.Model.PasswordlessToken;
import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordlessTokenRepo extends JpaRepository<PasswordlessToken,Long> {

    @Query("SELECT t FROM PasswordlessToken t WHERE t.token = ?1")
    PasswordlessToken findByToken(String token);
}
