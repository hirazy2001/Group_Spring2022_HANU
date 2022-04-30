package com.example.group_hanu_spring2022.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterAccountDto {

    private Long userID;

    private String accountStatus;

    private String accountType;

    private String pinCode;

    @Override
    public String toString() {
        return "RegisterAccountDto{" +
                "uId=" + userID +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
