package com.fursah.BankSystem.service.auth;

import com.fursah.BankSystem.bo.customeUserDetails.CustomUserDetails;
import com.fursah.BankSystem.entity.UserEntity;
import com.fursah.BankSystem.reposatriy.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return buildCustomUserDetailsOfUsername(s);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private CustomUserDetails buildCustomUserDetailsOfUsername(String username) throws NotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow();
        if (user == null) {
            throw new NotFoundException("User Not Found");
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(user.getId());
        userDetails.setName(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setRole(user.getRoles().getTitle().name());

        return userDetails;
    }
}
