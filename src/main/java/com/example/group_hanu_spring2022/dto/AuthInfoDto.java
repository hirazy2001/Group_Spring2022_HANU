package com.example.group_hanu_spring2022.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthInfoDto {

    public UserInfoDto userInfoDto;

    public String token;

}
