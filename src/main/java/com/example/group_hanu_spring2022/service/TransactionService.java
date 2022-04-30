package com.example.group_hanu_spring2022.service;

import com.example.group_hanu_spring2022.dto.TransactionAdminDto;
import com.example.group_hanu_spring2022.dto.TransactionDto;
import com.example.group_hanu_spring2022.model.Account;
import com.example.group_hanu_spring2022.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface TransactionService {

    List<TransactionAdminDto> getAllTransactions();

    TransactionDto createTransaction(TransactionDto transaction);

    TransactionDto createWithDrawTransaction(TransactionDto transaction);

    TransactionDto createSavingTransaction(Account account, TransactionDto transaction);

    TransactionAdminDto getTransaction(long id);

    Transaction updateTransaction(Transaction transaction);

    boolean deleteTransaction(long id);
}
