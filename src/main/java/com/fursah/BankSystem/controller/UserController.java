package com.fursah.BankSystem.controller;

import com.fursah.BankSystem.bo.user.CreateUserRequest;
import com.fursah.BankSystem.bo.user.UpdateUserRequest;
import com.fursah.BankSystem.service.suggestion.GuestSuggestionService;
import com.fursah.BankSystem.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService; //injection this is the constructor with similar class name

    private final GuestSuggestionService suggestionService;

    public UserController(UserService userService, GuestSuggestionService suggestionService) {
        this.userService = userService;
        this.suggestionService = suggestionService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            userService.saveUser(createUserRequest);


        }catch (IllegalArgumentException e){

            return ResponseEntity.badRequest().body(" ACTIVE or INACTIVE");
        }
        return ResponseEntity.ok(" User Has Been Created");
    }
    @PutMapping("/update")
    public  ResponseEntity<String>updateUser(@RequestParam Long userId, @RequestBody UpdateUserRequest updateUserRequest){
        try {
            userService.updateUserStaus(userId, updateUserRequest);


        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok(" User Has Been Updated");
    }
}