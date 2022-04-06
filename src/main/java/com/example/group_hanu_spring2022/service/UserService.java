package com.example.group_hanu_spring2022.service;

import com.example.group_hanu_spring2022.dto.AccountInfoDto;
import com.example.group_hanu_spring2022.dto.JWTDto;
import com.example.group_hanu_spring2022.dto.RegisterDto;
import com.example.group_hanu_spring2022.dto.UserInfoDto;
import com.example.group_hanu_spring2022.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface UserService {

    List<UserInfoDto> getAllUsers();

    UserInfoDto getMe(JWTDto token);

    UserInfoDto getUser(long id);

    List<AccountInfoDto> getAllAccounts(long id);

    UserInfoDto createUser(RegisterDto user);

    UserInfoDto updateUser(Long id, UserInfoDto user);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phoneNumber);

    boolean checkExistEmail(String email);

    void updatePassword(User user, String password);

    boolean delete(long id);
}
