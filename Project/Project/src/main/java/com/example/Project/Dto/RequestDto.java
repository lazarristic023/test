package com.example.Project.Dto;

import com.example.Project.Model.RequestStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
public class RequestDto {

    public long id;


    public String status;

    public long clientId;

    public LocalDate startDate;

    public LocalDate endDate;

    public RequestDto(){}

    public RequestDto(long id, String status, long clientId) {
        this.id = id;
        this.status = status;
        this.clientId = clientId;
    }
}
