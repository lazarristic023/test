package com.example.Project.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserTokenState {
    private String accessToken;
    private String refreshToken;
    private long refreshTokenExpireIn;
    private long accessTokenExpiresIn;
    private boolean tfaEnabled;
    private String secretImageUri;

    public UserTokenState(String token, long expiresIn,String refreshToken,long refreshTokenExpireIn, boolean tfaEnabled, String secretImageUri) {
        this.accessToken = token;
        this.accessTokenExpiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireIn = refreshTokenExpireIn;
        this.tfaEnabled = tfaEnabled;
        this.secretImageUri = secretImageUri;
    }
}
