package com.fursah.BankSystem.service.admin;

import com.fursah.BankSystem.entity.UserEntity;
import com.fursah.BankSystem.reposatriy.UserRepository;

import java.util.List;

public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
