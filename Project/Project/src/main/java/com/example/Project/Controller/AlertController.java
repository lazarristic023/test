package com.example.Project.Controller;

import com.example.Project.Dto.AlertDto;
import com.example.Project.Dto.ClientDto;
import com.example.Project.Model.Alert;
import com.example.Project.Model.Client;
import com.example.Project.Service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "= 'https://localhost:8081")
@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-all-unread")
    public ResponseEntity<List<AlertDto>> getAllUnread() {
        List<Alert> unreadAlerts = alertService.getAllUnread();
        List<AlertDto> dtos = new ArrayList<>();
        for(Alert a: unreadAlerts) {
            AlertDto dto = new AlertDto(a.getTitle(), a.getMessage(), a.isRead());
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @CrossOrigin(origins = "*")
    @PutMapping ("/read-alert/{id}")
    //@PreAuthorize("hasAuthority('client:update')")
    public ResponseEntity<Void> readAlert(@PathVariable Long id) {
        alertService.readAlert(id);
        return ResponseEntity.ok().build();
    }
}
