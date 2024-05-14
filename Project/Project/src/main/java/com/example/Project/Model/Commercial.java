package com.example.Project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="commercials")
@Getter
@Setter
public class Commercial {

    @Id
    private long id;

    @NotEmpty
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private long clientId;


    public Commercial(String description, Date start, Date end, long clientId) {
        this.description = description;
        this.startDate = start;
        this.endDate = end;
        this.clientId = clientId;
    }

    public Commercial() {

    }
}
