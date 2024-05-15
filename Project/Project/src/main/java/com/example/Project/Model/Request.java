package com.example.Project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="requests")
@Getter
@Setter
public class Request {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;


    public RequestStatus status;

    public String username;

    public Request(RequestStatus status, String username) {
        this.status = status;
        this.username = username;
    }

    public Request() {
    }
    @Column(nullable = true)
    public LocalDate startDate;

    @Column(nullable = true)
    public LocalDate endDate;
}
