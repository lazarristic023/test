package com.example.Project.Dto;

public class RefreshTokenRequest {
    private String refreshToken;
    private String username;
    private String password;


    public RefreshTokenRequest(String refreshToken, String username, String password) {
        this.refreshToken = refreshToken;
        this.username = username;
        this.password = password;
    }

    public RefreshTokenRequest(){}

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
