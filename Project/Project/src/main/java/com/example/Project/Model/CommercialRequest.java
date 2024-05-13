package com.example.Project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="commercialRequests")
@Getter
@Setter
public class CommercialRequest {

    @Id
    private long id;

    @NotEmpty
    private String description;

    @NotEmpty
    private Date createDeadline;

    @NotEmpty
    private Date start;

    @NotEmpty
    private Date end;

    @NotEmpty
    private long clientId;

    @NotEmpty
    private boolean isAccepted;

    public CommercialRequest(String description, Date createDeadline, Date start, Date end, long clientId, boolean isAccepted) {
        this.description = description;
        this.createDeadline = createDeadline;
        this.start = start;
        this.end = end;
        this.clientId = clientId;
        this.isAccepted = isAccepted;
    }

    public CommercialRequest() {
    }
}
