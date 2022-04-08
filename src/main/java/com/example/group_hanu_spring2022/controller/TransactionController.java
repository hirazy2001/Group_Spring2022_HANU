package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.dto.TransactionAdminDto;
import com.example.group_hanu_spring2022.dto.TransactionDto;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
import com.example.group_hanu_spring2022.exception.ResourceNotFoundException;
import com.example.group_hanu_spring2022.model.Account;
import com.example.group_hanu_spring2022.model.AccountType;
import com.example.group_hanu_spring2022.repository.AccountRepository;
import com.example.group_hanu_spring2022.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    /**
     * @api {get} / Get All Accounts
     * @apiName getAccounts
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TransactionAdminDto>> getAllTransactions(HttpServletRequest httpServletRequest) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if(!isAdmin){
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    /**
     * @api {get} / Get Transaction
     * @apiName getAccounts
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTransaction(@PathVariable Long id, HttpServletRequest httpServletRequest) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if(!isAdmin){
            return ResponseEntity.status(401).body(null);
        }

        TransactionAdminDto transaction = transactionService.getTransaction(id);

        if(transaction == null){
            return ResponseEntity.status(400).body(new ResourceNotFoundException("Transaction", id.toString(), null).getErrorMessage());
        }

        return ResponseEntity.ok(transaction);
    }

    /**
     * @api {post} / Create a Transaction
     * @apiName createTransaction
     * @apiGroup Transaction
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransferTransaction(
            @RequestBody TransactionDto transactionDto) {

        /**
         * Find 2 Account
         */
        Optional<Account> fromAccountOptional = accountRepository.findById(transactionDto.getFrom_account());

        if (fromAccountOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new ResourceNotFoundException("Account", "id", transactionDto.getFrom_account()).getErrorMessage());
        }

        Optional<Account> toAccountOptional = accountRepository.findById(transactionDto.getTo_account());

        if (toAccountOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new ResourceNotFoundException("Account", "id", transactionDto.getTo_account()).getErrorMessage());
        }

        /**
         * Compare balance of fromAccount to balance which he/she want to transact
         */
        Account fromAccount = fromAccountOptional.get();

        if(!fromAccount.getPinCode().equals(transactionDto.getPinCode())){
            return ResponseEntity.status(404).body(new ResourceNotFoundException("Account", "pin code", "not exact").getErrorMessage());
        }

        if (fromAccount.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            return ResponseEntity.status(404).body(new ResourceNotFoundException("Account", "balance", "not enough").getErrorMessage());
        }

        TransactionDto transaction = transactionService.createTransaction(transactionDto);

        return ResponseEntity.ok(transaction);
    }

    @RequestMapping(value = "/withdraw",
            method = RequestMethod.POST,
            consumes =  MimeTypeUtils.APPLICATION_JSON_VALUE
        )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createWithDrawTransaction(@RequestBody TransactionDto transactionDto){

        logger.error("createWithDrawTransaction" + transactionDto.toString());

        Optional<Account> fromAccountOptional = accountRepository.findById(transactionDto.getFrom_account());

        if (fromAccountOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new ResourceNotFoundException("Account", "id", transactionDto.getFrom_account()).getErrorMessage());
        }

        Account fromAccount = fromAccountOptional.get();

        if(!fromAccount.getPinCode().equals(transactionDto.getPinCode())){
            return ResponseEntity.status(404).body(new ResourceNotFoundException("Account", "pin code", "not exact").getErrorMessage());
        }

        if (fromAccount.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            return ResponseEntity.status(404).body(new ResourceNotFoundException("Account", "balance", "not enough").getErrorMessage());
        }

        TransactionDto transaction = transactionService.createWithDrawTransaction(transactionDto);

        return ResponseEntity.ok(transaction);
    }

    @RequestMapping(value = "/loan",
            method = RequestMethod.POST,
            consumes =  MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createLoanTransaction(@RequestBody TransactionDto transactionDto){

        Optional<Account> fromAccountOptional = accountRepository.findById(transactionDto.getFrom_account());

        if (fromAccountOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new ErrorMessage("Cannot found account!"));
        }

        Account fromAccount = fromAccountOptional.get();

        // Check Pin code
        if(!fromAccount.getPinCode().equals(transactionDto.getPinCode())){
            return ResponseEntity.status(404).body(new ErrorMessage("Pin code of account is not exact!"));
        }

        if(!fromAccount.getAccountType().equals(AccountType.LOAN)){
            return ResponseEntity.status(404).body(new ErrorMessage("Account is not loan account!"));
        }

        TransactionDto transaction = transactionService.createLoanTransaction(transactionDto);

        return ResponseEntity.ok(transaction);
    }

    @RequestMapping(value = "/saving",
            method = RequestMethod.POST,
            consumes =  MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN') OR hasRole('MODERATOR')")
    public ResponseEntity<?> createSavingTransaction(){

        return ResponseEntity.status(200).body(null);
    }


    /**
     * @api {delete} / Delete a Transaction
     * @apiName deleteTransaction
     * @apiGroup Transaction
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTransaction(Long id) {
        boolean isDeleted = transactionService.deleteTransaction(id);

        if(!isDeleted){
            return ResponseEntity.status(400).body(new ResourceNotFoundException("Transaction", "id", id).getErrorMessage());
        }
        return ResponseEntity.ok(null);
    }

}
