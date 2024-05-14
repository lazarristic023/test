package com.example.Project.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommercialDto {

    private String description;

    private Date startDate;

    private Date endDate;

    private long clientId;

    public CommercialDto() {
    }

    public CommercialDto(String description, Date start, Date end, long clientId) {
        this.description = description;
        this.startDate = start;
        this.endDate = end;
        this.clientId = clientId;
    }
}
