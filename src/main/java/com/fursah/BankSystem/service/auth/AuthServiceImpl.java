package com.fursah.BankSystem.service.auth;

import com.fursah.BankSystem.bo.auth.AuthenticationResponce;
import com.fursah.BankSystem.bo.auth.CreateLoginRequest;
import com.fursah.BankSystem.bo.auth.CreateSignupRequest;
import com.fursah.BankSystem.bo.auth.LogoutResponce;
import com.fursah.BankSystem.bo.customeUserDetails.CustomUserDetails;
import com.fursah.BankSystem.config.JWTUtil;
import com.fursah.BankSystem.entity.RoleEntity;
import com.fursah.BankSystem.entity.UserEntity;
import com.fursah.BankSystem.reposatriy.RoleRepo;
import com.fursah.BankSystem.reposatriy.UserRepository;
import com.fursah.BankSystem.util.enums.Roles;
import com.fursah.BankSystem.util.enums.Status;
import com.fursah.BankSystem.util.excption.BodyGaurdException;
import com.fursah.BankSystem.util.excption.UserNotFoundExcption;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailService;
    private final JWTUtil jwtUtil;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailService userDetailService, JWTUtil jwtUtil, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity= roleRepo.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow(() -> new BodyGaurdException("no Roles Found"));;
        UserEntity user= new UserEntity();
        user.setName(createSignupRequest.getName());
        user.setUsername(createSignupRequest.getUsername());
        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
        user.setEmail(createSignupRequest.getEmail());
        user.setRoles(roleEntity);
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }


    @Override
    public AuthenticationResponce login(CreateLoginRequest createLoginRequest) {
        requieredNonNull(createLoginRequest.getUsername(), "username");
        requieredNonNull(createLoginRequest.getPassword(), "password");

        String username = createLoginRequest.getUsername().toLowerCase();
        String password = createLoginRequest.getPassword();
        authentication(username, password);
        CustomUserDetails userDetails = userDetailService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponce responce = new AuthenticationResponce();
        responce.setUsername(userDetails.getUsername());
        responce.setRole(userDetails.getRole());
        responce.setToken("Bearer" + accessToken);
        return responce;
    }

    @Override
    public void logout(LogoutResponce logoutResponce) {
        requieredNonNull(logoutResponce.getToken(),"token");
    }

    private void requieredNonNull(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()) {
            throw new BodyGaurdException(name + "can not be empty");
        }
    }

    private void authentication(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BodyGaurdException e) {
            throw new BodyGaurdException("incorrect");
        } catch (AuthenticationServiceException e) {
            throw new UserNotFoundExcption("not found");
        }
    }
}
