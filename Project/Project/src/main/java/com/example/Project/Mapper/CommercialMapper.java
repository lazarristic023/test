package com.example.Project.Mapper;

import com.example.Project.Dto.CommercialDto;
import com.example.Project.Model.Commercial;
import org.springframework.stereotype.Component;

@Component
public class CommercialMapper {

    public CommercialDto mapToDto(Commercial commercial) {
        CommercialDto commercialDto = new CommercialDto();
        commercialDto.setDescription(commercial.getDescription());
        commercialDto.setStartDate(commercial.getStartDate());
        commercialDto.setEndDate(commercial.getEndDate());
        commercialDto.setClientId(commercialDto.getClientId());
        return commercialDto;
    }

    public Commercial mapToModel(CommercialDto commercialDto) {
        Commercial commercial = new Commercial();
        commercial.setDescription(commercialDto.getDescription());
        commercial.setStartDate(commercialDto.getStartDate());
        commercial.setEndDate(commercialDto.getEndDate());
        commercial.setClientId(commercialDto.getClientId());
        return commercial;
    }
}
