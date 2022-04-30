package com.example.group_hanu_spring2022.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoDto {

    public Long id;

    public String username;

    private String firstName;

    private String middleName;

    private String lastName;

    private boolean gender;

    private String phoneNumber;

    private String picture;

    private String dob;

    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", picture='" + picture + '\'' +
                ", dob=" + dob +
                ", createdAt=" + createdAt +
                '}';
    }
}
