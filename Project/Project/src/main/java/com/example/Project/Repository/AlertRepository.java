package com.example.Project.Repository;

import com.example.Project.Model.Alert;
import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Alert a SET a.isRead = :isRead WHERE a.id = :id")
    void updateIsReadById(Long id, boolean isRead);
}
