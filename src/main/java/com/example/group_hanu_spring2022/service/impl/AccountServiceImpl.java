package com.example.group_hanu_spring2022.service.impl;

import com.example.group_hanu_spring2022.dto.AccountInfoDto;
import com.example.group_hanu_spring2022.dto.RegisterAccountDto;
import com.example.group_hanu_spring2022.dto.TransactionInfoDto;
import com.example.group_hanu_spring2022.model.Account;
import com.example.group_hanu_spring2022.model.Transaction;
import com.example.group_hanu_spring2022.model.User;
import com.example.group_hanu_spring2022.repository.AccountRepository;
import com.example.group_hanu_spring2022.repository.TransactionRepository;
import com.example.group_hanu_spring2022.repository.UserRepository;
import com.example.group_hanu_spring2022.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ServiceHelper serviceHelper;

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public AccountInfoDto createAccount(RegisterAccountDto acctInfoDto) {


        Optional<User> user = userRepository.findById(acctInfoDto.getUserID());

        if (user.isEmpty()) {
            return null;
        }

        Account account = serviceHelper.convertRegisterToAccount(acctInfoDto);
        account.setuId(user.get());

        Account newAccount = accountRepository.save(account);

        return serviceHelper.convertToAccountInfo(newAccount);
    }

    @Override
    public AccountInfoDto getAccount(Long accountID) {
        Optional<Account> accountOptional = accountRepository.findById(accountID);

        if (accountOptional.isEmpty()) {
            return null;
        }

        Account account = accountOptional.get();

        AccountInfoDto accountInfoDto = serviceHelper.convertToAccountInfo(account);

        return accountInfoDto;
    }

    @Override
    public AccountInfoDto updateAccount(AccountInfoDto acct, Long id) {

        Optional<Account> account = accountRepository.findById(id);

        if(account.isEmpty()){
            return null;
        }



        return null;
    }

    @Override
    public void deleteAccount(Long accountID) {
        accountRepository.deleteById(accountID);
    }

    @Override
    public BigDecimal getAccountBalance(Long acctID) {

        Optional<Account> accountOptional = accountRepository.findById(acctID);

        if (accountOptional.isEmpty()) {
            return null;
        }

        Account account = accountOptional.get();

        return account.getBalance();
    }

    @Override
    public boolean deleteById(Long acctID) {

        Optional<Account> accountOptional = accountRepository.findById(acctID);

        if (accountOptional.isEmpty()) {
            return false;
        }

        accountRepository.deleteById(acctID);
        return true;
    }

    @Override
    public void saveBalanceByAcctID(Long accountID, BigDecimal amount) {

        accountRepository.saveBalanceByAcctID(accountID, amount);
    }

    @Override
    public List<TransactionInfoDto> getTransactions(Long acctID) {

        List<TransactionInfoDto> allTransactionInfoDto = new ArrayList<>();

        Iterable<Transaction> transactionList = transactionRepository.getTransactionRelation(acctID);

        transactionList.forEach(transaction -> {
            allTransactionInfoDto.add(serviceHelper.convertTransactionInfoDto(transaction, acctID));
        });

        return allTransactionInfoDto;
    }

    @Override
    public List<AccountInfoDto> getAllAccounts() {

        List<AccountInfoDto> allAccountInfoDto = new ArrayList<>();

        Iterable<Account> accountList = accountRepository.findAll();

        accountList.forEach(account -> {
            allAccountInfoDto.add(serviceHelper.convertToAccountInfo(account));
        });

        return allAccountInfoDto;
    }
}
