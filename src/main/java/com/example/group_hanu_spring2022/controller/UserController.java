package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.constants.Constants;
import com.example.group_hanu_spring2022.dto.*;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
import com.example.group_hanu_spring2022.model.User;
import com.example.group_hanu_spring2022.repository.AccountRepository;
import com.example.group_hanu_spring2022.repository.TransactionRepository;
import com.example.group_hanu_spring2022.repository.UserRepository;
import com.example.group_hanu_spring2022.service.UserService;
import com.example.group_hanu_spring2022.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtils;

    /**
     * @api {get} /users/ Get All Users
     * @apiName GetAllUsers
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers(HttpServletRequest httpServletRequest) {
        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if (!isAdmin) {
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * @api {get} /users/me Get Me
     * @apiName GetMe
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 Token is not exact.
     */
    @RequestMapping(value = "/me", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getMe(@RequestBody JWTDto tokenDto) {

        UserInfoDto userInfoDto = userService.getMe(tokenDto);

        if (userInfoDto == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Token is not exact!"));
        }

        return ResponseEntity.ok(userInfoDto);
    }

    /**
     * @api {get} /users/{id} Get user
     * @apiName GetUser
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser( // HttpServletRequest httpServletRequest,
                                     @PathVariable Long id) {

//        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");
//
//        if (!isAdmin) {
//            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
//        }

        UserInfoDto user = null;

        user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("User not found by id = " + id));
        }

        return ResponseEntity.ok(user);
    }

    /**
     * @api {get} users/{id}/accounts Get All Accounts
     * @apiName GetAllAccounts
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}/accounts", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllAccount(@PathVariable Long id) {
        UserInfoDto user = null;

        user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("User not found by id = " + id));
        }

        List<AccountInfoDto> accounts = userService.getAllAccounts(id);

        return ResponseEntity.ok(accounts);
    }

    /**
     * @api {get} /users/{id}/transactions Create user
     * @apiName GetUser
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}/transactions", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllTransaction(@PathVariable Long id) {
        UserInfoDto user = null;

        user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("User not found by id = " + id));
        }

        List<TransactionInfoDto> transactions = userService.getAllTransactions(id);

        return ResponseEntity.ok(transactions);
    }

    @RequestMapping(value = "/phone/{phoneNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> checkExistPhoneNumber(@PathVariable String phoneNumber) {
        boolean isExisted = userService.checkExistPhone(phoneNumber);
        return ResponseEntity.ok(isExisted);
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> checkExistUsername(@PathVariable String username) {
        boolean isExisted = userService.checkExistUsername(username);
        return ResponseEntity.ok(isExisted);
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> checkExistEmail(@PathVariable String email) {
        boolean isExisted = userService.checkExistEmail(email);
        return ResponseEntity.ok(isExisted);
    }

    /**
     * @api {post} /users/ Create user
     * @apiName createUser
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createUser(
            HttpServletRequest servletRequest,
            @RequestBody RegisterDto registerDto) {
        logger.error("An ERROR Message");

        if(registerDto.getDob() == null){
            return ResponseEntity.status(400).body(new ErrorMessage("Please input dob!"));
        }

        // Check Condition
        if (userService.checkExistUsername(registerDto.getUsername())) {
            return ResponseEntity.status(400).body(new ErrorMessage("UserName existed!"));
        }

        if (userService.checkExistPhone(registerDto.getPhoneNumber())) {
            return ResponseEntity.status(400).body(new ErrorMessage("Phone Number existed!"));
        }

        if(registerDto.getPhoneNumber().length() < 10 || registerDto.getPhoneNumber().length() > 11){
            return ResponseEntity.status(400).body(new ErrorMessage("Phone Number length must be 10 or 11 numbers!"));
        }

        if (userService.checkExistEmail(registerDto.getEmail())) {
            return ResponseEntity.status(400).body(new ErrorMessage("Email existed!"));
        }

        if (registerDto.getPassword().length() < 6) {
            return ResponseEntity.status(400).body(new ErrorMessage("Password must contain at least 6 characters!"));
        }

        UserInfoDto userInfoDto = userService.createUser(registerDto);

        if (userInfoDto == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Role is not found!"));
        }

        return ResponseEntity.ok(userInfoDto);
    }

    /**
     * @api {put} /users/ Update user
     * @apiName updateUser
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserInfoDto user) {
        UserInfoDto userInfoDto = null;

        logger.error(user.toString());

        UserInfoDto findUser = userService.getUser(id);

        if (findUser == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("User not found by id = " + id));
        }

        userInfoDto = userService.updateUser(id, user);

        return ResponseEntity.ok(userInfoDto);
    }

    /**
     * @api {put} /users/ Update user
     * @apiName updateUser
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updatePassword(HttpServletRequest httpServletRequest, @RequestBody PasswordDto passwordDto,
                                            @RequestHeader HttpHeaders headers) {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        String token = "";
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            token =  bearerToken.substring(7, bearerToken.length());
        }

        String username = jwtUtils.getUserNameFromJwtToken(token);

        // Compare password
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (passwordDto.getNewPassword().length() < 6) {
            return ResponseEntity.status(400).body(new ErrorMessage("Password must contain at least 6 characters!"));
        }

        if(passwordDto.getOldPassword().equals(passwordDto.getNewPassword())){
            return ResponseEntity.status(400).body(new ErrorMessage("Your new password cannot be same as old password!"));
        }

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Cannot found user by username!"));
        }

        UserInfoDto userInfoDto = userService.updatePassword(userOptional.get(), passwordDto);

        if (userInfoDto == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Password is not exact!"));
        }

        return ResponseEntity.ok(userInfoDto);
    }

    /**
     * @api {delete} /users/{id} Create account
     * @apiName CreateAccount
     * @apiGroup Account
     * @apiPermission admin
     * @apiHeader {String} Authorization Bearer authorization with token.
     * @apiSuccess (Success 200) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 400 [User not found, Deleted User Failed].
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteUser(HttpServletRequest httpServletRequest, @PathVariable Long id) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if(!isAdmin){
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        if (userService.getUser(id) == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("User not found by id = " + id));
        }


        boolean isDeleted = userService.delete(id);

        if (!isDeleted) {
            return ResponseEntity.status(400).body(new ErrorMessage("Deleted User Failed!"));
        }
        return ResponseEntity.ok(null);
    }
}
