package com.fursah.BankSystem.service.user;

import com.fursah.BankSystem.bo.user.UpdateUserRequest;
import com.fursah.BankSystem.util.enums.Status;
import com.fursah.BankSystem.entity.UserEntity;
import com.fursah.BankSystem.reposatriy.UserRepository;
import com.fursah.BankSystem.bo.user.CreateUserRequest;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhone());
        if (!createUserRequest.getStatus().equals(Status.ACTIVE.name()) || !createUserRequest.getStatus().equals(Status.INACTIVE.name())) {
            throw new IllegalArgumentException("Status should be written either ACTIVE or INACTIVE");

        }
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUserStaus(UpdateUserRequest updateUserRequest) {

    }

    @Override
    public List<String> getAllusersWithStrongPassword() {
        return null;
    }

    @Override
    public void updateUserStatus(Long userId, UpdateUserRequest updateUserStatusRequest) {

    }


    @Override
    public List<String> getALlUsersWithStrongPassword() {
        return userRepository.findAll().stream()
                .filter(user -> user.getPassword().length() > 8)
                .map(UserEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserStaus(Long userId, UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow();
        if (!updateUserRequest.getStatus().equals("ACTIVE") && !updateUserRequest.getStatus().equals("INACTIVE")) {
            throw new IllegalArgumentException("Status should be written either ACTIVE or INACTIVE");

        }


    }
}

