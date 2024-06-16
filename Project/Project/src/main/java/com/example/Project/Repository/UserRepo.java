package com.example.Project.Repository;

import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
    public interface UserRepo extends JpaRepository<User,Long> {
        @Query("SELECT u FROM User u WHERE u.username = ?1")
        User findByUsername(String username);

        @Query("SELECT u FROM User u WHERE u.email = ?1")
        User findByEmail(String email);

        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.email = ?2 WHERE u.id = ?1")
        void updateEmailById(Long id, String email);

        @Override
        List<User> findAll();

        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.username = ?2 WHERE u.id = ?1")
        void updateUsernameById(Long id, String username);

        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.password = ?2 WHERE u.id = ?1")
        void updatePasswordById(Long id, String password);

}

