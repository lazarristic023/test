package com.example.Project.Controller;

import com.example.Project.Dto.ClientDto;
import com.example.Project.Dto.CommercialRequestDto;
import com.example.Project.Mapper.CommercialRequestMapper;
import com.example.Project.Model.Client;
import com.example.Project.Model.CommercialRequest;
import com.example.Project.Service.CommercialRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
