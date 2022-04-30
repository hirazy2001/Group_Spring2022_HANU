package com.example.group_hanu_spring2022.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class JWTResponseDto {

    private Long id;
    private String type = "Basic";
    private String username;
    private List roles;
    private String accessToken;

    public JWTResponseDto(Long id, String type, String username,  List roles, String accessToken) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.roles = roles;
        this.accessToken = accessToken;
    }
}
