package com.example.Project.Controller;

import com.example.Project.Dto.RequestDto;
import com.example.Project.Model.Request;
import com.example.Project.Model.RequestStatus;
import com.example.Project.Model.User;
import com.example.Project.Service.EmailService;
import com.example.Project.Service.RequestService;
import com.example.Project.Service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
          dto.setStartDate(req.getStartDate());
          dto.setEndDate(req.getEndDate());
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
        try {
            emailService.sendEmail(client);
        } catch (NoSuchAlgorithmException | InvalidKeyException | MessagingException | UnsupportedEncodingException e) {

            e.printStackTrace();
        }


        RequestDto updatedDto= new RequestDto(req.getId(),req.getStatus().toString(),req.getClientId());
        updatedDto.setStartDate(req.getStartDate());
        updatedDto.setEndDate(req.getEndDate());
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/reject/{reason}")
    public ResponseEntity<RequestDto> reject(@RequestBody Request request, @PathVariable String reason){
        request.setStatus(RequestStatus.REJECTED);
        LocalDate currentDate= LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(2);
        request.setStartDate(futureDate);
        Request req=requestService.create(request);

        User client= userService.getById(request.getClientId());
        //salji mejl
        emailService.sendRejectedEmail(client,reason);
        RequestDto updatedDto= new RequestDto(req.getId(),req.getStatus().toString(),req.getClientId());
        updatedDto.setStartDate(req.getStartDate());
        updatedDto.setEndDate(req.getEndDate());
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getByClientId/{clientId}")
    public ResponseEntity<RequestDto> getByClientId(@PathVariable Long clientId){
        Request request=requestService.getByClientId(clientId);
        RequestDto dto = new RequestDto();
        if(request!=null  ) {
             dto = new RequestDto(request.getId(), request.getStatus().toString(), request.getClientId());
            dto.setStartDate(request.getStartDate());
            dto.setEndDate(request.getEndDate());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
