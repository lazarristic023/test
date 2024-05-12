package com.example.Project.Controller;

import com.example.Project.Dto.RequestDto;
import com.example.Project.Model.Request;
import com.example.Project.Model.RequestStatus;
import com.example.Project.Model.User;
import com.example.Project.Model.UserTokenState;
import com.example.Project.Service.EmailService;
import com.example.Project.Service.RequestService;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    public RequestService requestService;


    @Autowired
    public EmailService emailService;

    @Autowired
    public UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getAll")
    public ResponseEntity<List<RequestDto>> createAuthenticationToken(){
        List<Request> requests= requestService.getAllRequests();
        List<RequestDto> dtos= new ArrayList<>();
        for(Request req: requests){
          RequestDto dto= new RequestDto(req.getId(),req.getStatus().toString(),req.getClientId());
          dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



    @CrossOrigin(origins = "*")
    @PutMapping("/accept")
    public ResponseEntity<RequestDto> accept(@RequestBody Request request){
        request.setStatus(RequestStatus.ACCEPTED);
        Request req=requestService.create(request);
        User client= userService.getById(request.getClientId());
        //salji mejl
        emailService.sendEmail(client);
        RequestDto updatedDto= new RequestDto(req.getId(),req.getStatus().toString(),req.getClientId());
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/reject/{reason}")
    public ResponseEntity<RequestDto> reject(@RequestBody Request request, @PathVariable String reason){
        request.setStatus(RequestStatus.REJECTED);
        Request req=requestService.create(request);
        User client= userService.getById(request.getClientId());
        //salji mejl
        emailService.sendRejectedEmail(client,reason);
        RequestDto updatedDto= new RequestDto(req.getId(),req.getStatus().toString(),req.getClientId());
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

}
