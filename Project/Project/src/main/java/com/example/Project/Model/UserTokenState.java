package com.example.Project.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenState {
    private String accessToken;
    private String refreshToken;
    private long refreshTokenExpireIn;
    private long accessTokenExpiresIn;

    public UserTokenState(String token, long expiresIn,String refreshToken,long refreshTokenExpireIn) {
        this.accessToken = token;
        this.accessTokenExpiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireIn = refreshTokenExpireIn;
    }
}
