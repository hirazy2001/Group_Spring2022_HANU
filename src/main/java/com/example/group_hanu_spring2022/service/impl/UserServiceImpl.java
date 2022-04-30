package com.example.group_hanu_spring2022.service.impl;

import com.example.group_hanu_spring2022.constants.Constants;
import com.example.group_hanu_spring2022.dto.*;
import com.example.group_hanu_spring2022.model.Account;
import com.example.group_hanu_spring2022.model.Role;
import com.example.group_hanu_spring2022.model.RoleName;
import com.example.group_hanu_spring2022.model.User;
import com.example.group_hanu_spring2022.repository.AccountRepository;
import com.example.group_hanu_spring2022.repository.RoleRepository;
import com.example.group_hanu_spring2022.repository.UserRepository;
import com.example.group_hanu_spring2022.service.AccountService;
import com.example.group_hanu_spring2022.service.UserService;
import com.example.group_hanu_spring2022.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ServiceHelper serviceHelper;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserInfoDto> getAllUsers() {
        List<UserInfoDto> allUserInfoDto = new ArrayList<>();

        Iterable<User> userList = userRepository.findAll();

        userList.forEach(user -> {
            allUserInfoDto.add(serviceHelper.convertToUserInfoDto(user));
        });

        return allUserInfoDto;
    }


    @Override
    public UserInfoDto getMe(JWTDto token) {
        boolean isValid = jwtUtil.validateJwtToken(token.getToken());

        if (!isValid) {
            return null;
        }

        String userName = jwtUtil.getUserNameFromJwtToken(token.getToken());

        logger.error("USERNAME " + userName);

        Optional<User> user = userRepository.findUserByUsername(userName);
        if (user.isEmpty()) {
            return null;
        }

        return serviceHelper.convertToUserInfoDto(user.get());
    }

    @Override
    public UserInfoDto getUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        logger.error("getUser " + user.toString());

        UserInfoDto userInfoDto = serviceHelper.convertToUserInfoDto(user);
        return userInfoDto;
    }

    @Override
    public List<AccountInfoDto> getAllAccounts(long id) {

        List<AccountInfoDto> list = new ArrayList<>();

        Iterable<Account> accounts = accountRepository.findAccountByUId(id);


        accounts.forEach((account -> {
            list.add(serviceHelper.convertToAccountInfo(account));
        }));

        logger.error("Accounts Length " + list.size());

        return list;
    }

    @Override
    public List<TransactionInfoDto> getAllTransactions(Long userId) {

        List<AccountInfoDto> list = getAllAccounts(userId);

        List<TransactionInfoDto> listTransaction = new ArrayList<>();

        list.forEach((account -> {
            List<TransactionInfoDto> listTmp = accountService.getTransactions(account.getId());

            listTmp.forEach((transactionDto -> {
                listTransaction.add(transactionDto);
            }));
        }));

        return listTransaction;
    }

    /**
     * @param registerDto
     * @return
     * @implSpec Create User and DEFAULT USER
     */
    @Override
    public UserInfoDto createUser(RegisterDto registerDto) {
        User user = serviceHelper.convertToUser(registerDto);

        user.setRole(Constants.ROLE_USER);

        Set<String> strRoles = registerDto.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(RoleName.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        User newUser = userRepository.save(user);

        UserInfoDto dto = serviceHelper.convertToUserInfoDto(newUser);

        logger.error(dto.toString());

        return dto;
    }

    @Override
    public UserInfoDto updateUser(Long id, UserInfoDto user) {
        User foundUser = userRepository.getById(id);

        if (user.getFirstName() != null) {
            foundUser.setFirstName(user.getFirstName());
        }

        if (user.getMiddleName() != null) {
            foundUser.setMiddleName(user.getMiddleName());
        }

        if (user.getLastName() != null) {
            foundUser.setLastName(user.getLastName());
        }

        if (user.getPicture() != null) {
            foundUser.setPicture(user.getPicture());
        }

        if (user.getDob() != null) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM/uuuu")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();
            LocalDateTime dateTime = LocalDateTime.parse(user.getDob(), formatter);

            foundUser.setDob(dateTime);
        }

        if (user.getPhoneNumber() != null) {
            foundUser.setPhoneNumber(user.getPhoneNumber());
        }

        User savedUser = userRepository.save(foundUser);

        return serviceHelper.convertToUserInfoDto(savedUser);
    }

    @Override
    public boolean checkExistUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);

        if (!user.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkExistPhone(String phoneNumber) {
        Optional<User> user = userRepository.findUserByPhoneNumber(phoneNumber);

        if (!user.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (!user.isEmpty()) {
            return true;
        }
        return false;
    }

    public User getByResetPasswordToken(String token) {
        return null;
    }

    @Override
    public UserInfoDto updatePassword(User user, PasswordDto passwordDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        logger.error(user.getPassword() + " " + passwordDto.getOldPassword());
        boolean isChecked = passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword());

        if (!isChecked) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));

        return serviceHelper.convertToUserInfoDto(userRepository.save(user));
    }

    @Override
    public boolean delete(long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return false;
        }

        userRepository.deleteById(id);

        return true;
    }
}
