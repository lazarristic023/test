package com.example.Project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="commercialRequests")
@Getter
@Setter
public class CommercialRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String description;

    @NotNull
    private Date createDeadlineDate;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private long clientId;

    private boolean isAccepted;

    public CommercialRequest(String description, Date createDeadline, Date start, Date end, long clientId, boolean isAccepted) {
        this.description = description;
        this.createDeadlineDate = createDeadline;
        this.startDate = start;
        this.endDate = end;
        this.clientId = clientId;
        this.isAccepted = isAccepted;
    }

    public CommercialRequest() {
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
