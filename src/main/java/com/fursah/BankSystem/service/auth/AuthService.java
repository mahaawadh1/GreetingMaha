package com.fursah.BankSystem.service.auth;

import com.fursah.BankSystem.bo.auth.AuthenticationResponce;
import com.fursah.BankSystem.bo.auth.CreateLoginRequest;
import com.fursah.BankSystem.bo.auth.CreateSignupRequest;
import com.fursah.BankSystem.bo.auth.LogoutResponce;

public interface AuthService {
    void signup(CreateSignupRequest createSignupRequest);
    AuthenticationResponce login(CreateLoginRequest createLoginRequest);
    void logout(LogoutResponce logoutResponce);
}
