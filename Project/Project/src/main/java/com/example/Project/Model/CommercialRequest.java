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
    private String Description;

    @NotEmpty
    private Date CreateDeadline;

    @NotEmpty
    private Date Start;

    @NotEmpty
    private Date End;

    @NotEmpty
    private long ClientId;

    @NotEmpty
    private boolean IsAccepted;

    public CommercialRequest(String description, Date createDeadline, Date start, Date end, long clientId, boolean isAccepted) {
        Description = description;
        CreateDeadline = createDeadline;
        Start = start;
        End = end;
        ClientId = clientId;
        IsAccepted = isAccepted;
    }

    public CommercialRequest() {
    }
}
