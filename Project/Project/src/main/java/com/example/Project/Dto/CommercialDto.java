package com.example.Project.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommercialDto {

    private String Description;

    private Date Start;

    private Date End;

    private long ClientId;

    public CommercialDto() {
    }

    public CommercialDto(String description, Date start, Date end, long clientId) {
        Description = description;
        Start = start;
        End = end;
        ClientId = clientId;
    }
}
