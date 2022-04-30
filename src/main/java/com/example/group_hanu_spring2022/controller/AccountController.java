package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.constants.Constants;
import com.example.group_hanu_spring2022.dto.AccountInfoDto;
import com.example.group_hanu_spring2022.dto.RegisterAccountDto;
import com.example.group_hanu_spring2022.dto.TransactionInfoDto;
import com.example.group_hanu_spring2022.dto.UserInfoDto;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
import com.example.group_hanu_spring2022.model.Account;
import com.example.group_hanu_spring2022.model.request.UploadFile;
import com.example.group_hanu_spring2022.repository.AccountRepository;
import com.example.group_hanu_spring2022.repository.TransactionRepository;
import com.example.group_hanu_spring2022.service.AccountService;
import com.example.group_hanu_spring2022.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 *
 */

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    public String UPLOAD_DIR = System.getProperty("account.home") + "/upload";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * @api {get} / Get All Accounts
     * @apiName getAccounts
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAccounts(HttpServletRequest httpServletRequest) {
        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if (!isAdmin) {
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    /**
     * @api {post} /accounts/ Create account
     * @apiName CreateAccount
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 400 [User not found, Pin code must at least 6 numbers, Pin code must contain only number].
     */
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@Valid @RequestBody RegisterAccountDto account) {

        if (account.getPinCode().length() != 6) {
            return ResponseEntity.status(400).body(new ErrorMessage("Pin code must contain at least 6 numbers!"));
        }

        // Check type of pin code
        for(int i = 0; i < 6;i++){
            if(account.getPinCode().charAt(i) < '0' || account.getPinCode().charAt(i) > '9'){
                return ResponseEntity.status(400).body(new ErrorMessage("Pin code must contain only number!"));
            }
        }

        UserInfoDto optionalUser = userService.getUser(account.getUserID());

        if (optionalUser == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("User not found by id = " + account.getUserID()));
        }

        AccountInfoDto accountInfoDto = accountService.createAccount(account);

        if (accountInfoDto == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("User not found by id = " + account.getUserID()));
        }

        return ResponseEntity.ok(accountInfoDto);
    }

    /**
     * @api {get} /accounts/ Get ME
     * @apiName CreateAccount
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Account getMe(@RequestBody String token) {
        return null;
    }

    /**
     * @api {get} /accounts/:id Get Account By ID
     * @apiName DeleteUser
     * @apiGroup User
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getAccountById(
            // HttpServletRequest httpServletRequest,
                                            @PathVariable Long id) {

//        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");
//
//        if (!isAdmin) {
//            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
//        }

        AccountInfoDto account = accountService.getAccount(id);

        if (account == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + id));
        }

        return ResponseEntity.ok(account);
    }

    /**
     * @api {delete} /accounts/:id/balance Get Balance
     * @apiName GetBalance
     * @apiGroup Account
     * @apiPermission user
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 Account not found.
     */
    @RequestMapping(value = "/{id}/balance", method = RequestMethod.GET)
    public ResponseEntity<?> getBalance(@PathVariable Long id) {
        BigDecimal balance = accountService.getAccountBalance(id);

        if (balance == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + id));
        }
        return ResponseEntity.ok(balance);
    }

    /**
     * @api {get} /accounts/:id/transactions Get All Transactions
     * @apiName GetAllTransactions
     * @apiGroup Account
     * @apiPermission user
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}/transactions", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactions(@PathVariable Long id) {

        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + id));
        }

        List<TransactionInfoDto> list = accountService.getTransactions(id);

        if (list == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("List Transactions not found!"));
        }

        return ResponseEntity.ok(list);
    }

    /**
     * @api {delete} /accounts/:id Delete account
     * @apiName DeleteAccount
     * @apiGroup User
     * @apiPermission admin
     * @apiHeader {String} Authorization Bearer authorization with token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@ModelAttribute("uploadForm") UploadFile form) {
        // Create folder to save file if not exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile fileData = form.getFileData();
        String name = fileData.getOriginalFilename();
        if (name != null && name.length() > 0) {
            try {
                // Create file
                File serverFile = new File(UPLOAD_DIR + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                return ResponseEntity.ok("/file/" + name);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when uploading");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }

    /**
     * @api {put} /accounts/:id Update account
     * @apiName UpdateAccount
     * @apiGroup Account
     * @apiPermission user
     * @apiHeader {String} Authorization Bearer authorization with token.
     * @apiSuccess (Success 200) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 400 [Pin code must contain 6 numbers, Pin code is wrong].
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountInfoDto account, @PathVariable Long id) {
        if (account.getPinCode().length() != 6) {
            return ResponseEntity.status(400).body(new ErrorMessage("Pin code must contain 6 numbers!"));
        }

        AccountInfoDto accountInfoDto = accountService.updateAccount(account, id);

        if (accountInfoDto == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Pin code is wrong!"));
        }

        return ResponseEntity.ok(accountInfoDto);
    }

    /**
     * @api {delete} /accounts/:id Delete account
     * @apiName DeleteAccount
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 400 [Account not found, Deleted Account Failed].
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(HttpServletRequest httpServletRequest, @PathVariable Long id) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if (!isAdmin) {
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        if (accountService.getAccount(id) == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Account not found by id = " + id));
        }

        boolean isDeleted = accountService.deleteById(id);

        if (!isDeleted) {
            return ResponseEntity.status(400).body(new ErrorMessage("Deleted Account Failed!"));
        }
        return ResponseEntity.ok(null);
    }

}
