package com.example.Project.Model;

import com.example.Project.Enum.RequestStatus;
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

    public long clientId;
}
