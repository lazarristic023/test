package com.example.Project.Controller;

import com.example.Project.Dto.CommercialDto;
import com.example.Project.Dto.RequestDto;
import com.example.Project.Mapper.CommercialMapper;
import com.example.Project.Model.Commercial;
import com.example.Project.Model.Request;
import com.example.Project.Service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/commercial")
public class CommercialController {

    @Autowired
    private CommercialService commercialService;

    @Autowired
    private CommercialMapper commercialMapper;

    public CommercialController(CommercialService commercialService, CommercialMapper commercialMapper) {
        this.commercialService = commercialService;
        this.commercialMapper = commercialMapper;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-by/{clientId}")
    public ResponseEntity<List<CommercialDto>> getClientCommercials(@PathVariable long clientId){
        List<Commercial> commercials = commercialService.findByClientId(clientId);
        List<CommercialDto> dtoList = new ArrayList<>();
        for(Commercial c: commercials){
            dtoList.add(commercialMapper.mapToDto(c));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}