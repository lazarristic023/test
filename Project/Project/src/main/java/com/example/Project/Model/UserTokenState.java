package com.example.Project.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenState {
    private String token;
    private long expiresIn;

    public UserTokenState(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
