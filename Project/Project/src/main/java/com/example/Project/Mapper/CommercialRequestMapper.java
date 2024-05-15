package com.example.Project.Mapper;

import com.example.Project.Dto.CommercialDto;
import com.example.Project.Dto.CommercialRequestDto;
import com.example.Project.Model.Commercial;
import com.example.Project.Model.CommercialRequest;
import org.springframework.stereotype.Component;

@Component
public class CommercialRequestMapper {

    public CommercialRequestDto mapToDto(CommercialRequest commercialRequest) {
        CommercialRequestDto commercialRequestDto = new CommercialRequestDto();
        commercialRequestDto.setDescription(commercialRequest.getDescription());
        commercialRequestDto.setCreateDeadlineDate(commercialRequest.getCreateDeadlineDate());
        commercialRequestDto.setStartDate(commercialRequest.getStartDate());
        commercialRequestDto.setEndDate(commercialRequest.getEndDate());
        commercialRequestDto.setClientId(commercialRequest.getClientId());
        commercialRequestDto.setAccepted(commercialRequest.isAccepted());
        return commercialRequestDto;
    }

    public CommercialRequest mapToModel(CommercialRequestDto commercialRequestDto) {
        CommercialRequest commercialRequest = new CommercialRequest();
        commercialRequest.setDescription(commercialRequestDto.getDescription());
        commercialRequest.setCreateDeadlineDate(commercialRequestDto.getCreateDeadlineDate());
        commercialRequest.setStartDate(commercialRequestDto.getStartDate());
        commercialRequest.setEndDate(commercialRequestDto.getEndDate());
        commercialRequest.setClientId(commercialRequestDto.getClientId());
        commercialRequest.setAccepted(commercialRequestDto.isAccepted());
        return commercialRequest;
    }
}
