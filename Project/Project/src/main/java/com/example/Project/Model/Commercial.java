package com.example.Project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private Date start;

    @NotEmpty
    private Date end;

    @NotEmpty
    private long clientId;


    public Commercial(String description, Date start, Date end, long clientId) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.clientId = clientId;
    }

    public Commercial() {

    }
}
