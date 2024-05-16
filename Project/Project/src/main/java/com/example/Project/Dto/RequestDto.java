package com.example.Project.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RequestDto {

    public long id;


    public String status;

    public String username;

    public LocalDate startDate;

    public LocalDate endDate;

    public RequestDto(){}

    public RequestDto(long id, String status, String username) {
        this.id = id;
        this.status = status;
        this.username = username;
    }
}
