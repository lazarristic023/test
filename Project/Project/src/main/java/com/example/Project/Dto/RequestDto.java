package com.example.Project.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {

    public long id;


    public String status;

    public String username;

    public RequestDto(){}

    public RequestDto(long id, String status, String username) {
        this.id = id;
        this.status = status;
        this.username = username;
    }
}
