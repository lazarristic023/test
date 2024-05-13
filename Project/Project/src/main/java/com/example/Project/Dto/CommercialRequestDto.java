package com.example.Project.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommercialRequestDto {

    private String description;

    private Date createDeadline;

    private Date start;

    private Date end;

    private long clientId;

    private boolean isAccepted;

    public CommercialRequestDto() {
    }

    public CommercialRequestDto(String description, Date createDeadline, Date start, Date end, long clientId, boolean isAccepted) {
        this.description = description;
        this.createDeadline = createDeadline;
        this.start = start;
        this.end = end;
        this.clientId = clientId;
        this.isAccepted = isAccepted;
    }
}
