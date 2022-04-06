package com.example.group_hanu_spring2022.service.impl;

import com.example.group_hanu_spring2022.constants.Constants;
import com.example.group_hanu_spring2022.dto.*;
import com.example.group_hanu_spring2022.model.Account;
import com.example.group_hanu_spring2022.model.AccountType;
import com.example.group_hanu_spring2022.model.Transaction;
import com.example.group_hanu_spring2022.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ServiceHelper {

    Logger logger = LoggerFactory.getLogger(ServiceHelper.class);

    public AccountInfoDto convertToAccountInfo(Account account) {
        return AccountInfoDto.builder()
                .id(account.getId())
                .uId(account.getuId().getId())
                .accountType(account.getAccountType() == null ? AccountType.LOAN : account.getAccountType())
                .accountStatus(account.getAccountStatus())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt() == null ? Constants.DEFAULT_TIME : account.getCreatedAt())
                .pinCode(account.getPinCode())
                .build();
    }

    public Account convertToAccount(AccountInfoDto account) {
        return Account.builder()
                .id(account.getId())
                .accountType(account.getAccountType() == null ? AccountType.LOAN : account.getAccountType())
                .accountStatus(account.getAccountStatus())
                .balance(account.getBalance() == null ? new BigDecimal(0) : account.getBalance())
                .createdAt(account.getCreatedAt() == null ? Constants.DEFAULT_TIME : account.getCreatedAt())
                .updatedAt(account.getUpdatedAt() == null ? Constants.DEFAULT_TIME : account.getUpdatedAt())
                .build();
    }

    public Account convertRegisterToAccount(RegisterAccountDto accountDto) {
        return Account.builder()
                .accountType(accountDto.getAccountType().equals(AccountType.PRIMARY.toString()) ?
                        AccountType.PRIMARY : (accountDto.getAccountType().equals(AccountType.SAVING.toString()) ?
                        AccountType.SAVING : AccountType.LOAN
                ))
                .accountStatus(accountDto.getAccountStatus())
                .pinCode(accountDto.getPinCode())
                .balance(new BigDecimal(0))
                .createdAt(Constants.DEFAULT_TIME)
                .updatedAt(Constants.DEFAULT_TIME)
                .build();
    }

    public TransactionInfoDto convertTransactionInfoDto(Transaction transaction, Long accountID) {
        return TransactionInfoDto.builder()
                .id(transaction.getId())
                .account(accountID)
                .isPlus(transaction.getFromAccount().getId() == accountID ? false : true)
                .amount(transaction.getAmount())
                .purpose(transaction.getPurpose())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

    public TransactionAdminDto convertTransactionAdminDto(Transaction transaction) {
        return TransactionAdminDto.builder()
                .id(transaction.getId())
                .from_account(transaction.getFromAccount().getId())
                .to_account(transaction.getToAccount() == null ? null : transaction.getToAccount().getId())
                .amount(transaction.getAmount())
                .purpose(transaction.getPurpose())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

//    public TransactionDto convertTransactionDto(Transaction transaction, Long accountID) {
//        return TransactionDto.builder()
//                .account(accountID)
//                .amount(transaction.getAmount())
//                .purpose(transaction.getPurpose())
//                .createdAt(transaction.getCreatedAt())
//                .build();
//    }

    public User convertToUser(RegisterDto registerDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());

        return User.builder()
                .username(registerDto.getUsername())
                .password(encodedPassword)
                .firstName(registerDto.getFirstName())
                .middleName(registerDto.getMiddleName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .dob(registerDto.getDob())
                .phoneNumber(registerDto.getPhoneNumber())
                .address(registerDto.getAddress())
                .gender(registerDto.isGender())
                .build();
    }

    public UserInfoDto convertToUserInfoDto(User user) {
        UserInfoDto dto = UserInfoDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .dob(user.getDob() == null ? Constants.DEFAULT_TIME : user.getDob())
                .gender(user.isGender())
                .username(user.getUsername())
                .picture(user.getPicture() == null ? Constants.DEFAULT_ICON : user.getPicture())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt() == null ? Constants.DEFAULT_TIME : user.getCreatedAt())
                .build();

        logger.error("UserInfoDto " + dto.toString());
        return dto;
    }


}
