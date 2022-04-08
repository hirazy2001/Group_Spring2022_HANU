package com.example.group_hanu_spring2022.service.impl;

import com.example.group_hanu_spring2022.dto.TransactionAdminDto;
import com.example.group_hanu_spring2022.dto.TransactionDto;
import com.example.group_hanu_spring2022.model.Account;
import com.example.group_hanu_spring2022.model.Transaction;
import com.example.group_hanu_spring2022.repository.AccountRepository;
import com.example.group_hanu_spring2022.repository.TransactionRepository;
import com.example.group_hanu_spring2022.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ServiceHelper serviceHelper;

    @Override
    public List<TransactionAdminDto> getAllTransactions() {
        List<TransactionAdminDto> transactionAdminDtoList = new ArrayList<>();

        Iterable<Transaction> list = transactionRepository.findAll();

        list.forEach((transaction -> {
            transactionAdminDtoList.add(serviceHelper.convertTransactionAdminDto(transaction));
        }));

        return transactionAdminDtoList;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {


        LocalDateTime time = LocalDateTime.now();

        // Sender
        accountRepository.saveBalanceByAcctID(transactionDto.getFrom_account(),
                accountRepository.getById(transactionDto.getFrom_account()).getBalance().
                        subtract(transactionDto.getAmount()));

        // Receiver
        accountRepository.saveBalanceByAcctID(transactionDto.getTo_account(),
                accountRepository.getById(transactionDto.getTo_account()).getBalance().
                        add(transactionDto.getAmount())
        );

        Transaction transaction = Transaction.builder()
                .amount(transactionDto.getAmount())
                .fromAccount(accountRepository.getById(transactionDto.getFrom_account()))
                .toAccount(accountRepository.getById(transactionDto.getTo_account()))
                .createdAt(time)
                .purpose(transactionDto.getPurpose())
                .build();

        Transaction newTransaction = transactionRepository.save(transaction);

        TransactionDto infoTransaction = TransactionDto.builder()
                .to_account(transactionDto.getTo_account())
                .amount(newTransaction.getAmount())
                .purpose(newTransaction.getPurpose())
                .from_account(transactionDto.getFrom_account())
                .build();
        return infoTransaction;
    }

    @Override
    public TransactionDto createWithDrawTransaction(TransactionDto transactionDto) {
        LocalDateTime time = LocalDateTime.now();

        Account account = accountRepository.findById(transactionDto.getFrom_account()).get();

        // Sender
        accountRepository.saveBalanceByAcctID(transactionDto.getFrom_account(),
                accountRepository.getById(transactionDto.getFrom_account()).getBalance().
                        subtract(transactionDto.getAmount()));

        Transaction transaction = Transaction.builder()
                .amount(transactionDto.getAmount())
                .fromAccount(account)
                .createdAt(time)
                .purpose(transactionDto.getPurpose())
                .build();

        Transaction newTransaction = transactionRepository.save(transaction);

        TransactionDto infoTransaction = TransactionDto.builder()
                                            .amount(newTransaction.getAmount())
                .purpose(newTransaction.getPurpose())
                .from_account(transactionDto.getFrom_account())
                .build();
        return infoTransaction;
    }

    @Override
    public TransactionDto createLoanTransaction(TransactionDto transactionDto) {

        LocalDateTime time = LocalDateTime.now();

        Account account = accountRepository.findById(transactionDto.getFrom_account()).get();

        // Sender
        accountRepository.saveBalanceByAcctID(transactionDto.getFrom_account(),
                accountRepository.getById(transactionDto.getFrom_account()).getBalance().
                        subtract(transactionDto.getAmount()));

        Transaction transaction = Transaction.builder()
                .amount(transactionDto.getAmount())
                .fromAccount(account)
                .createdAt(time)
                .purpose(transactionDto.getPurpose())
                .build();

        Transaction newTransaction = transactionRepository.save(transaction);

        TransactionDto infoTransaction = TransactionDto.builder()
                .amount(newTransaction.getAmount())
                .purpose(newTransaction.getPurpose())
                .from_account(transactionDto.getFrom_account())
                .build();
        return infoTransaction;
    }

    @Override
    public TransactionAdminDto getTransaction(long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        if (transaction.isEmpty()) {
            return null;
        }

        TransactionAdminDto transactionAdminDto = serviceHelper.convertTransactionAdminDto(transaction.get());

        return transactionAdminDto;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public boolean deleteTransaction(long id) {
        Optional<Transaction> userOptional = transactionRepository.findById(id);

        if (userOptional.isEmpty()) {
            return false;
        }

        transactionRepository.deleteById(id);

        return true;
    }
}
