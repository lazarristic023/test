package com.example.Project.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommercialRequestDto {

    private String Description;

    private Date CreateDeadline;

    private Date Start;

    private Date End;

    private long ClientId;

    private boolean IsAccepted;

    public CommercialRequestDto() {
    }

    public CommercialRequestDto(String description, Date createDeadline, Date start, Date end, long clientId, boolean isAccepted) {
        Description = description;
        CreateDeadline = createDeadline;
        Start = start;
        End = end;
        ClientId = clientId;
        IsAccepted = isAccepted;
    }
}
