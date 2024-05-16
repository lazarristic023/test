package com.example.Project.Controller;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Dto.CommercialDto;
import com.example.Project.Dto.CommercialRequestDto;
import com.example.Project.Mapper.CommercialRequestMapper;
import com.example.Project.Model.Client;
import com.example.Project.Model.CommercialRequest;
import com.example.Project.Service.CommercialRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/commercial-request")
public class CommercialRequestController {

    @Autowired
    private CommercialRequestService commercialRequestService;

    @Autowired
    private CommercialRequestMapper commercialRequestMapper;

    public CommercialRequestController(CommercialRequestService commercialRequestService, CommercialRequestMapper commercialRequestMapper) {
        this.commercialRequestService = commercialRequestService;
        this.commercialRequestMapper = commercialRequestMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<CommercialRequestDto> createCommercialRequest(@RequestBody CommercialRequestDto commercialRequestDto) {
        CommercialRequest commercialRequest = commercialRequestMapper.mapToModel(commercialRequestDto);
        CommercialRequest savedRequest = commercialRequestService.save(commercialRequest);
        CommercialRequestDto savedRequestDto = commercialRequestMapper.mapToDto(savedRequest);
        return ResponseEntity.ok(savedRequestDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CommercialRequestDto>> getAll() {
        List<CommercialRequest> commercialRequests= commercialRequestService.getAll();
        List<CommercialRequestDto> dtos= new ArrayList<>();
        for(CommercialRequest r : commercialRequests){
            CommercialRequestDto dto = new CommercialRequestDto(r.getDescription(), r.getCreateDeadlineDate(), r.getStartDate(), r.getEndDate(), r.getClientId(), r.isAccepted());
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/accept")
    public ResponseEntity<CommercialRequestDto> acceptRequest(@RequestBody CommercialRequestDto commercialRequestDto) {
       // CommercialRequest req= new CommercialRequest(commercialRequestDto.getDescription(),commercialRequestDto.getCreateDeadlineDate(),commercialRequestDto.getStartDate(),commercialRequestDto.getEndDate(),commercialRequestDto.getClientId(),true);
        CommercialRequest existing = commercialRequestService.getByClient(commercialRequestDto.getClientId());
        existing.setAccepted(true);
        commercialRequestService.save(existing);
        return ResponseEntity.ok(commercialRequestDto);
    }


}
