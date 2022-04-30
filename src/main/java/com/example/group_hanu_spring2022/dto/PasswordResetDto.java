package com.example.group_hanu_spring2022.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordResetDto {

    private String username;
    private String phoneNumber;
    private String email;
}
