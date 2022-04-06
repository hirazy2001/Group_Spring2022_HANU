package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.dto.PasswordResetDto;
import com.example.group_hanu_spring2022.dto.ResetDto;
import com.example.group_hanu_spring2022.dto.UserInfoDto;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
import com.example.group_hanu_spring2022.model.PasswordResetToken;
import com.example.group_hanu_spring2022.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password-resets")
public class PasswordResetController {

    @Autowired
    PasswordResetService passwordResetService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createPasswordResetToken(
            @RequestBody PasswordResetDto passwordResetDto
    ) {
        PasswordResetToken passwordResetToken = passwordResetService.create(passwordResetDto);

        if(passwordResetToken == null){
            return ResponseEntity.status(402).body(new ErrorMessage("Cannot find User with info!"));
        }

        return ResponseEntity.ok(passwordResetToken);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetDto resetDto
    ) {

        if(resetDto.getPassword().length() < 6){
            return ResponseEntity.status(402).body(new ErrorMessage("Password Length must be equal or more than 6 characters"));
        }

        UserInfoDto userInfoDto = passwordResetService.resetPassword(resetDto);

        if(userInfoDto == null){
            return ResponseEntity.status(402).body(new ErrorMessage("Can be error token or expire token!"));
        }

        return ResponseEntity.ok(userInfoDto);
    }
}
