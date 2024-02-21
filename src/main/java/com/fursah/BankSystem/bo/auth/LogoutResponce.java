package com.fursah.BankSystem.bo.auth;

public class LogoutResponce {
    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    private String Token;
}
