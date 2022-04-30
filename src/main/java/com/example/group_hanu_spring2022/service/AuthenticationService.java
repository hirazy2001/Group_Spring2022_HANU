package com.example.group_hanu_spring2022.service;

import com.example.group_hanu_spring2022.dto.AuthInfoDto;
import com.example.group_hanu_spring2022.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthInfoDto authentication(LoginDto loginDto);
}
