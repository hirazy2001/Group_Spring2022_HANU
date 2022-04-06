package com.example.group_hanu_spring2022.service;

import com.example.group_hanu_spring2022.dto.AccountInfoDto;
import com.example.group_hanu_spring2022.dto.RegisterAccountDto;
import com.example.group_hanu_spring2022.dto.TransactionInfoDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
    AccountInfoDto createAccount(RegisterAccountDto acct);

    AccountInfoDto getAccount(Long accountID);

    AccountInfoDto updateAccount(AccountInfoDto acct);

    void deleteAccount(Long accountID);

    BigDecimal getAccountBalance(Long accountID);

    boolean deleteById(Long acctID);

    void saveBalanceByAcctID(Long accountID, BigDecimal amount);

    List<TransactionInfoDto> getTransactions(Long acctID);

    List<AccountInfoDto> getAllAccounts();
}
