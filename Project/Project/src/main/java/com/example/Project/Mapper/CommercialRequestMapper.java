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
        commercialRequestDto.setCreateDeadline(commercialRequest.getCreateDeadline());
        commercialRequestDto.setStart(commercialRequest.getStart());
        commercialRequestDto.setEnd(commercialRequest.getEnd());
        commercialRequestDto.setClientId(commercialRequest.getClientId());
        commercialRequestDto.setAccepted(commercialRequest.isAccepted());
        return commercialRequestDto;
    }

    public CommercialRequest mapToModel(CommercialRequestDto commercialRequestDto) {
        CommercialRequest commercialRequest = new CommercialRequest();
        commercialRequest.setDescription(commercialRequestDto.getDescription());
        commercialRequest.setCreateDeadline(commercialRequestDto.getCreateDeadline());
        commercialRequest.setStart(commercialRequestDto.getStart());
        commercialRequest.setEnd(commercialRequestDto.getEnd());
        commercialRequest.setClientId(commercialRequestDto.getClientId());
        commercialRequest.setAccepted(commercialRequestDto.isAccepted());
        return commercialRequest;
    }
}
