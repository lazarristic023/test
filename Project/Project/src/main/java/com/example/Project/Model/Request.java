package com.example.Project.Model;

import com.example.Project.Enum.RequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
  
   @Column(nullable = true)
    public LocalDate startDate;

    @Column(nullable = true)
    public LocalDate endDate;

    public Request(RequestStatus status, String username) {
        this.status = status;
        this.username = username;
    }

    public Request() {
    }

    

   

}
