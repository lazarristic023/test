package com.example.Project.Controller;

import com.example.Project.Model.Request;
import com.example.Project.Model.UserTokenState;
import com.example.Project.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    public RequestService requestService;

    @CrossOrigin(origins = "*")
    @PostMapping("/getAll")
    public ResponseEntity<List<Request>> createAuthenticationToken(){
        List<Request> requests= requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    
}
