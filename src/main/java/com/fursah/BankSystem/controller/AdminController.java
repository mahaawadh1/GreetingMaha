package com.fursah.BankSystem.controller;

import com.fursah.BankSystem.entity.UserEntity;
import com.fursah.BankSystem.service.admin.AdminService;
import com.fursah.BankSystem.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;


    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }
    public ResponseEntity<List<String>> getAllUsersWithStrongPasssword(){
        List<String> getAllusers= userService.getALlUsersWithStrongPassword();
        return ResponseEntity.ok(getAllusers);

    }
    @GetMapping("/get_users")
    public List<UserEntity> getAllUsers(){
        return adminService.getAllUsers();
    }
}