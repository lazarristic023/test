package com.example.Project.Dto;

import com.example.Project.Enum.RequestStatus;
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

    public RequestDto(String status, String username) {
        this.status = status;
        this.username = username;
    }
}
