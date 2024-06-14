package com.example.Project.Service;

import com.example.Project.Model.Alert;
import com.example.Project.Repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<Alert> getAllUnread() {
        List<Alert> unreadAlerts = new ArrayList<>();
        List<Alert> allAlerts = alertRepository.findAll();
        for(Alert a: allAlerts) {
            if(!a.isRead()) {
                unreadAlerts.add(a);
            }
        }

        return unreadAlerts;
    }

    public void readAlert(Long id) { alertRepository.updateIsReadById(id, true); }

    public Alert saveAlert(Alert alert) { return alertRepository.save(alert); }
}
