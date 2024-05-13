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
    private String Description;

    @NotEmpty
    private Date Start;

    @NotEmpty
    private Date End;

    @NotEmpty
    private long ClientId;


    public Commercial(String description, Date start, Date end, long clientId) {
        Description = description;
        Start = start;
        End = end;
        ClientId = clientId;
    }

    public Commercial() {

    }
}
