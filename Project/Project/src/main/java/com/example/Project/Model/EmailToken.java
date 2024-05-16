package com.example.Project.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tokens")
@Getter
@Setter
public class EmailToken {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public LocalDateTime expirationDate;

    public long clientId;

    public String token;

    public Boolean isUsed;

    public EmailToken(){}
    public EmailToken(LocalDateTime expirationDate, long clientId, String token, Boolean isUsed) {
        this.expirationDate = expirationDate;
        this.clientId = clientId;
        this.token = token;
        this.isUsed = isUsed;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}
