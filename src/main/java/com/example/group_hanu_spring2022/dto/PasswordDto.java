package com.example.group_hanu_spring2022.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordDto {

    private String oldPassword;

    private  String token;

    // @ValidPassword
    private String newPassword;
}
