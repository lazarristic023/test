package com.example.Project.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommercialRequestDto {

    private String description;

    private Date createDeadlineDate;

    private Date startDate;

    private Date endDate;

    private long clientId;

    private boolean isAccepted;

    public CommercialRequestDto() {
    }

    public CommercialRequestDto(String description, Date createDeadline, Date start, Date end, long clientId, boolean isAccepted) {
        this.description = description;
        this.createDeadlineDate = createDeadline;
        this.startDate = start;
        this.endDate = end;
        this.clientId = clientId;
        this.isAccepted = isAccepted;
    }
}
