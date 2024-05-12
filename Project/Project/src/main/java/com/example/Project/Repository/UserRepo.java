package com.example.Project.Repository;

import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



    @Repository
    public interface UserRepo extends JpaRepository<User,Long> {
        @Query("SELECT u FROM User u WHERE u.username = ?1")
        User findByUsername(String username);
    }

