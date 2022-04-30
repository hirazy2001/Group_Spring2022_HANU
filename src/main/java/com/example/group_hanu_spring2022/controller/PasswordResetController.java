package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.dto.PasswordResetDto;
import com.example.group_hanu_spring2022.dto.ResetDto;
import com.example.group_hanu_spring2022.dto.UserInfoDto;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
import com.example.group_hanu_spring2022.model.PasswordResetToken;
import com.example.group_hanu_spring2022.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/password-resets")
public class PasswordResetController {

    @Autowired
    PasswordResetService passwordResetService;

    /**
     * @api {get} /accounts/:id Get Account By ID
     * @apiName DeleteUser
     * @apiGroup User
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 Cannot find User with info.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createPasswordResetToken(
            @RequestBody PasswordResetDto passwordResetDto
    ) {
        PasswordResetToken passwordResetToken = passwordResetService.create(passwordResetDto);

        if(passwordResetToken == null){
            return ResponseEntity.status(400).body(new ErrorMessage("Cannot find User with info!"));
        }

        return ResponseEntity.ok(passwordResetToken);
    }

    /**
     * @api {get} /accounts/:id Get Account By ID
     * @apiName DeleteUser
     * @apiGroup User
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 [Can be error token or expire token, Password must contain at least 6 characters].
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetDto resetDto
    ) {

        if(resetDto.getPassword().length() < 6){
            return ResponseEntity.status(400).body(new ErrorMessage("Password must contain at least 6 characters"));
        }

        UserInfoDto userInfoDto = passwordResetService.resetPassword(resetDto);

        if(userInfoDto == null){
            return ResponseEntity.status(400).body(new ErrorMessage("Can be error token or expire token!"));
        }

        return ResponseEntity.ok(userInfoDto);
    }
}
