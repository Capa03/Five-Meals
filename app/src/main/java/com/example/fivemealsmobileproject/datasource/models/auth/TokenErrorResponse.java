package com.example.fivemealsmobileproject.datasource.models.auth;

public class TokenErrorResponse {
    private final String code;
    private final String description;

    public TokenErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
