package com.example.Project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="alerts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Alert {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String message;

    @NotEmpty
    private boolean isRead;

    public Alert() {
    }

    public Alert(String title, String message, boolean isRead) {
        this.title = title;
        this.message = message;
        this.isRead = isRead;
    }
}
