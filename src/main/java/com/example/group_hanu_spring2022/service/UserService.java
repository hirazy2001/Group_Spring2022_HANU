package com.example.group_hanu_spring2022.service;

import com.example.group_hanu_spring2022.dto.*;
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

    List<TransactionInfoDto> getAllTransactions(Long userId);

    UserInfoDto createUser(RegisterDto user);

    UserInfoDto updateUser(Long id, UserInfoDto user);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phoneNumber);

    boolean checkExistEmail(String email);

    UserInfoDto updatePassword(User user, PasswordDto password);

    boolean delete(long id);
}
