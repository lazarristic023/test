package com.example.Project.Utilities;

import com.example.Project.Model.Alert;
import com.example.Project.Model.User;
import com.example.Project.Service.AlertService;
import com.example.Project.Service.EmailService;
import com.example.Project.Service.SMSService;
import com.example.Project.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Component
public class LogMonitor {

    @Value("${LOGS}")
    private String logsDirectory;
    private List<User> allAdministrators = new ArrayList<>();
    private WatchService watchService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AlertService alertService;

    private static final Logger logger = LoggerFactory.getLogger(LogMonitor.class);

    @PostConstruct
    public void init() {
        try {
            Path logDirPath = Paths.get(logsDirectory);
            watchService = FileSystems.getDefault().newWatchService();
            logDirPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

            Executors.newSingleThreadExecutor().submit(() -> {
                while (true) {
                    try {
                        WatchKey key = watchService.take();
                        for (WatchEvent<?> event : key.pollEvents()) {
                            Path logFilePath = (Path) event.context();
                            // Obrada log fajla ili događaja
                            processLogFile(logDirPath.resolve(logFilePath));
                        }
                        key.reset();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLogFile(Path logFilePath) {
        // Implementirajte logiku za čitanje i obradu log fajlova ovde
        try {
            Files.lines(logFilePath).forEach(line -> {
                // Logika za analizu ili filtriranje linija log fajla
                // Možete implementirati filtriranje za kritične događaje ili greške
                if (line.contains("ERROR") || line.contains("WARN")) {
                    // Slanje upozorenja ili druge akcije
                    try {
                        sendAlert("Critical event recorded: " + line);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAlert(String message) throws Exception {
        // Implementirajte logiku za slanje upozorenja, na primer putem push notifikacija ili email-a
        getAllAdministrators();
        logger.warn("Warn: {}", message);
        // Ovde bi bila logika za slanje upozorenja administratoru
        Alert alert = new Alert("System warning: critical event", message, false);
        alertService.saveAlert(alert);
        for(User u: allAdministrators) {
            //String email = AESUtil.decrypt(u.getEmail());
            emailService.sendWarningEmail(u.getEmail(), "System warning: critical event", message);
            //String phone = AESUtil.decrypt(u.getPhone());
            //smsService.sendSMS(u.getPhone(), message);
            //Alert alert = new Alert("System warning: critical event", message, false);
            //alertService.saveAlert(alert);
        }
    }

    private void getAllAdministrators() throws Exception {
        allAdministrators.clear();
        List<User> allUsers = userService.findAll();
        for(User u: allUsers) {
            if(u.getRole().name().equals("ADMINISTRATOR")) {
                allAdministrators.add(u);
            }
        }
    }
}

