package com.example.group_hanu_spring2022.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginDto implements Serializable {

    String username;
    String password;

    public LoginDto(String userName, String passWord){
        this.username = userName;
        this.password = passWord;
    }
}
