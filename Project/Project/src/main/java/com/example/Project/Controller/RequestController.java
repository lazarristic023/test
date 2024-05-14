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
          RequestDto dto= new RequestDto(req.getId(),req.getStatus().toString(),req.getUsername());
          dto.setStartDate(req.getStartDate());
          dto.setEndDate(req.getEndDate());
          dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create/{username}")
    public ResponseEntity<RequestDto> createRequest(@PathVariable String username){
        Request request= requestService.create(new Request(RequestStatus.WAITING, username));
        RequestDto dto= new RequestDto(request.getStatus().toString(),request.getUsername());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping("/accept")
    public ResponseEntity<RequestDto> accept(@RequestBody Request request){
        request.setStatus(RequestStatus.ACCEPTED);
        Request req=requestService.create(request);
        User client= userService.findByUsername(request.getUsername());
        //salji mejl
       // emailService.sendEmail(client);
        RequestDto updatedDto= new RequestDto(req.getId(),req.getStatus().toString(),req.getUsername());
        try {
            emailService.sendEmail(client);
        } catch (NoSuchAlgorithmException | InvalidKeyException | MessagingException | UnsupportedEncodingException e) {

            e.printStackTrace();
        }


        RequestDto updatedDto= new RequestDto(req.getId(),req.getStatus().toString(),req.getUsername());
        updatedDto.setStartDate(req.getStartDate());
        updatedDto.setEndDate(req.getEndDate());
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/reject/{reason}")
    public ResponseEntity<RequestDto> reject(@RequestBody Request request, @PathVariable String reason){
        request.setStatus(RequestStatus.REJECTED);
        Request req=requestService.create(request);
        User client= userService.findByUsername(request.getUsername());
        //salji mejl
        emailService.sendRejectedEmail(client,reason);
        RequestDto updatedDto= new RequestDto(req.getId(),req.getStatus().toString(),req.getUsername());
        updatedDto.setStartDate(req.getStartDate());
        updatedDto.setEndDate(req.getEndDate());
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<RequestDto> getByClientId(@PathVariable String username){
        Request request=requestService.getByClientId(username);
        RequestDto dto = new RequestDto();
        if(request!=null  ) {
             dto = new RequestDto(request.getId(), request.getStatus().toString(), request.getUsername());
            dto.setStartDate(request.getStartDate());
            dto.setEndDate(request.getEndDate());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
