package com.example.Project.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="passwordlessTokens")
@Getter
@Setter
public class PasswordlessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private boolean isUsed;

    public PasswordlessToken() {}

    public PasswordlessToken(String token, boolean isUsed) {
        this.token = token;
        this.isUsed = isUsed;
    }
}
