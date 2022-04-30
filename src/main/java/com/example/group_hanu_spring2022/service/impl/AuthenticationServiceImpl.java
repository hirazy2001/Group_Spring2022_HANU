package com.example.group_hanu_spring2022.service.impl;

import com.example.group_hanu_spring2022.dto.AuthInfoDto;
import com.example.group_hanu_spring2022.dto.LoginDto;
import com.example.group_hanu_spring2022.model.User;
import com.example.group_hanu_spring2022.repository.UserRepository;
import com.example.group_hanu_spring2022.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceHelper serviceHelper;

    @Override
    public AuthInfoDto authentication(LoginDto loginDto) {

        Optional<User> userOptional = userRepository.findUserByUsername(loginDto.getUsername());

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        /**
         * Compare Password
         */
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matched = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());

        // Authentication Failed
        if (!matched) {

        }

        AuthInfoDto authInfoDto = AuthInfoDto.builder()
                .userInfoDto(
                        serviceHelper.convertToUserInfoDto(user)
                )
                .token("")
                .build();

        return authInfoDto;
    }
}
