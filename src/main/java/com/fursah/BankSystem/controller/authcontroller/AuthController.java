package com.fursah.BankSystem.controller.authcontroller;

import com.fursah.BankSystem.bo.auth.AuthenticationResponce;
import com.fursah.BankSystem.bo.auth.CreateLoginRequest;
import com.fursah.BankSystem.bo.auth.CreateSignupRequest;
import com.fursah.BankSystem.bo.auth.LogoutResponce;
import com.fursah.BankSystem.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("signup")
    public ResponseEntity<String>createUser(@RequestBody CreateSignupRequest createSignupRequest){
        try {
            authService.signup(createSignupRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("created");
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    public ResponseEntity<AuthenticationResponce> login(@RequestBody CreateLoginRequest createLoginRequest){
        AuthenticationResponce responce= authService.login(createLoginRequest);
        HttpStatus status=HttpStatus.OK;
        if (responce==null){
            status=HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responce,status);
    }
    @PostMapping("logout")
    public ResponseEntity<Void>logout(@RequestBody LogoutResponce logoutResponce){
        authService.logout(logoutResponce);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
