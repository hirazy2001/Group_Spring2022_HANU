package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.constants.Constants;
import com.example.group_hanu_spring2022.dto.SuccessMessage;
import com.example.group_hanu_spring2022.dto.TransactionAdminDto;
import com.example.group_hanu_spring2022.dto.TransactionDto;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
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
import java.math.BigDecimal;
import java.util.Optional;


@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    /**
     * @api {get} / Get All Transactions
     * @apiName getAllTransactions
     * @apiGroup Transactions
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 401 Admin can only access.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllTransactions(HttpServletRequest httpServletRequest) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if (!isAdmin) {
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    /**
     * @api {get} / Get Transaction
     * @apiName getAccounts
     * @apiGroup Account
     * @apiPermission admin
     * @apiHeader {String} Authorization Bearer authorization with token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 Transaction not found.
     * @apiError 401 Admin access only.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTransaction(HttpServletRequest httpServletRequest, @PathVariable Long id) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if (!isAdmin) {
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        TransactionAdminDto transaction = transactionService.getTransaction(id);

        if (transaction == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Transaction not found by id = " + id));
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
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + transactionDto.getFrom_account()));
        }

        Optional<Account> toAccountOptional = accountRepository.findById(transactionDto.getTo_account());

        if (toAccountOptional.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + transactionDto.getTo_account()));
        }

        /**
         * Compare balance of fromAccount to balance which he/she want to transact
         */
        Account fromAccount = fromAccountOptional.get();

        if (!fromAccount.getPinCode().equals(transactionDto.getPinCode())) {
            return ResponseEntity.status(400).body(new ErrorMessage("Pin code is not exact!"));
        }

        if (fromAccount.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            return ResponseEntity.status(400).body(new ErrorMessage("Balance of account is not enough!"));
        }

        TransactionDto transaction = transactionService.createTransaction(transactionDto);

        return ResponseEntity.ok(transaction);
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
    @RequestMapping(value = "/withdraw",
            method = RequestMethod.POST,
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createWithDrawTransaction(@RequestBody TransactionDto transactionDto) {

        logger.error("createWithDrawTransaction" + transactionDto.toString());

        Optional<Account> fromAccountOptional = accountRepository.findById(transactionDto.getFrom_account());

        if (fromAccountOptional.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + transactionDto.getFrom_account()));
        }

        Account fromAccount = fromAccountOptional.get();

        if (fromAccount.getAccountType().equals(AccountType.LOAN)) {
            return ResponseEntity.status(400).body(new ErrorMessage("Loan Account cannot create transaction!"));
        }

        if (!fromAccount.getPinCode().equals(transactionDto.getPinCode())) {
            return ResponseEntity.status(400).body(new ErrorMessage("Pin code is not exact!"));
        }

        if (fromAccount.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            return ResponseEntity.status(400).body(new ErrorMessage("Balance of account is not enough!"));
        }

        TransactionDto transaction = transactionService.createWithDrawTransaction(transactionDto);

        return ResponseEntity.ok(transaction);
    }

//    @RequestMapping(value = "/saving",
//            method = RequestMethod.POST,
//            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<?> createSavingTransaction(@RequestBody TransactionDto transactionDto) {
//
//        Optional<Account> fromAccountOptional = accountRepository.findById(transactionDto.getFrom_account());
//
//        TransactionDto transactionDto1 = transactionService.createSavingTransaction(transactionDto);
//
//        if (fromAccountOptional.isEmpty()) {
//            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + transactionDto.getFrom_account()));
//        }
//
//        return ResponseEntity.ok(transactionDto1);
//    }

    /**
     * @api {delete} / Delete a Transaction
     * @apiName deleteTransaction
     * @apiGroup Transaction
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/loan",
            method = RequestMethod.POST,
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> createLoanTransaction(@RequestBody TransactionDto transactionDto) {

        Optional<Account> fromAccountOptional = accountRepository.findById(transactionDto.getFrom_account());

        if (fromAccountOptional.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + transactionDto.getFrom_account()));
        }

        Account fromAccount = fromAccountOptional.get();

        if (!fromAccount.getAccountType().equals(AccountType.LOAN)) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account is not loan account!"));
        }

        TransactionDto transaction = transactionService.createWithDrawTransaction(transactionDto);

        return ResponseEntity.ok(transaction);
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
    @RequestMapping(value = "/saving",
            method = RequestMethod.POST,
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> createSavingTransaction(@RequestBody TransactionDto transactionDto) {

        logger.error("createWithDrawTransaction" + transactionDto.toString());

        Optional<Account> account = accountRepository.findById(transactionDto.getFrom_account());

        if (account.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found with id = " + transactionDto.getFrom_account()));
        }

        // Get Account
        Account curAccount = account.get();

        if (!curAccount.getAccountType().equals(AccountType.SAVING)) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account is not Saving account!"));
        }

        if (curAccount.getBalance().compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity.status(400).body(new ErrorMessage("Balance of Account is 0!"));
        }

        TransactionDto transaction = transactionService.createSavingTransaction(curAccount, transactionDto);

        if (transaction == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found with id = " + transactionDto.getFrom_account()));
        }

        return ResponseEntity.ok(transaction);
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseEntity<?> create(){
//        return ResponseEntity.ok(null);
//    }

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
    public ResponseEntity<?> deleteTransaction(HttpServletRequest httpServletRequest, @PathVariable Long id) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if (!isAdmin) {
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        if (transactionService.getTransaction(id) == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Transaction not found by id = " + id));
        }

        boolean isDeleted = transactionService.deleteTransaction(id);

        if (!isDeleted) {
            return ResponseEntity.status(400).body(new ErrorMessage("Deleted Transaction Failed!"));
        }
        return ResponseEntity.ok(new SuccessMessage("Deleted Transaction Successfully!"));
    }

}
