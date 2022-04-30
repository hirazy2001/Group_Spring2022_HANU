package com.example.group_hanu_spring2022.service;

import com.example.group_hanu_spring2022.dto.PasswordResetDto;
import com.example.group_hanu_spring2022.dto.ResetDto;
import com.example.group_hanu_spring2022.dto.UserInfoDto;
import com.example.group_hanu_spring2022.model.PasswordResetToken;
import org.springframework.stereotype.Service;

@Service
public interface PasswordResetService {

    public PasswordResetToken create(PasswordResetDto passwordResetDto);

    public UserInfoDto resetPassword(ResetDto resetDto);
}
