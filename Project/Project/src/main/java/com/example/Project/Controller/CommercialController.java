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


    @CrossOrigin(origins = "*")
    @GetMapping("/getAll")
    public ResponseEntity<List<CommercialDto>> getAll(){
        List<Commercial> commercials = commercialService.getAll();
        List<CommercialDto> dtoList = new ArrayList<>();
        for(Commercial c: commercials){
          CommercialDto dto= new CommercialDto(c.getDescription(),c.getStartDate(),c.getEndDate(),c.getClientId());
          dtoList.add(dto);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public ResponseEntity<CommercialDto> create(@RequestBody CommercialDto commercialDto){
        Commercial commercial= new Commercial(commercialDto.getDescription(),commercialDto.getStartDate(),commercialDto.getEndDate(),commercialDto.getClientId());
        Commercial saved = commercialService.create(commercial);

        return new ResponseEntity<>(commercialDto, HttpStatus.OK);
    }

}
