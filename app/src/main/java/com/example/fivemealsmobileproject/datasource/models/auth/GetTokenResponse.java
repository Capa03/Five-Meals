package com.example.fivemealsmobileproject.datasource.models.auth;

public class GetTokenResponse {

    private String token;
    private String expiration;

    public GetTokenResponse(String token, String expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public String getExpiration() {
        return expiration;
    }
}
