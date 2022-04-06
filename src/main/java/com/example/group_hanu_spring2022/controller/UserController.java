package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.dto.AccountInfoDto;
import com.example.group_hanu_spring2022.dto.JWTDto;
import com.example.group_hanu_spring2022.dto.RegisterDto;
import com.example.group_hanu_spring2022.dto.UserInfoDto;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
import com.example.group_hanu_spring2022.exception.ResourceNotFoundException;
import com.example.group_hanu_spring2022.repository.AccountRepository;
import com.example.group_hanu_spring2022.repository.TransactionRepository;
import com.example.group_hanu_spring2022.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * @api {get} /users/ Get All Users
     * @apiName GetAllUsers
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInfoDto>> getAllUsers(HttpServletRequest httpServletRequest) {
        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if(!isAdmin){
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(userService.getAllUsers());
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
    @RequestMapping(value = "/me", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getMe(@RequestBody JWTDto tokenDto) {

        UserInfoDto userInfoDto = userService.getMe(tokenDto);

        if (userInfoDto == null) {
            return ResponseEntity.status(404).body(new ResourceNotFoundException("User", "me", "").getErrorMessage());
        }

        return ResponseEntity.ok(userInfoDto);
    }

    /**
     * @api {get} /users/{id} Create user
     * @apiName GetUser
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserInfoDto user = null;

        user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.status(400).body(new ResourceNotFoundException("User", id.toString(), null).getErrorMessage());
        }

        return ResponseEntity.ok(user);
    }

    /**
     * @api {get} /users/{id} Create user
     * @apiName GetUser
     * @apiGroup Account
     * @apiPermission admin
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
            return ResponseEntity.status(400).body(new ResourceNotFoundException("User", id.toString(), null).getErrorMessage());
        }

        List<AccountInfoDto> accounts = userService.getAllAccounts(id);

        return ResponseEntity.ok(accounts);
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

        // Check Condition
        if(userService.checkExistUsername(registerDto.getUsername())){
            return ResponseEntity.status(400).body(new ErrorMessage("UserName existed!"));
        }

        if(userService.checkExistPhone(registerDto.getPhoneNumber())){
            return ResponseEntity.status(400).body(new ErrorMessage("Phone Number existed!"));
        }

        if(userService.checkExistEmail(registerDto.getEmail())){
            return ResponseEntity.status(400).body(new ErrorMessage("Email existed!"));
        }

        if(registerDto.getPassword().length() < 6){
            return ResponseEntity.status(400).body(new ErrorMessage("Password must be equal or more than 6 characters!"));
        }

        UserInfoDto userInfoDto = userService.createUser(registerDto);

        if (userInfoDto == null) {
            return ResponseEntity.status(400).body(new ResourceNotFoundException("User", "id.toString()", null).getErrorMessage());
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
            return ResponseEntity.status(400).body(new ResourceNotFoundException("User", "ID", id).getErrorMessage());
        }

        userInfoDto = userService.updateUser(id, user);

        return ResponseEntity.ok(userInfoDto);
    }

    /**
     * @api {delete} /users/{id} Create account
     * @apiName CreateAccount
     * @apiGroup Account
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 204) 204 No Content.
     * @apiError 401 Admin access only.
     * @apiError 404 User not found.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteUser(Long id) {
        if (userService.getUser(id) == null) {
            return ResponseEntity.status(400).body(new ResourceNotFoundException("User", id.toString(), null).getErrorMessage());
        }
        userService.delete(id);
        return ResponseEntity.ok(null);
    }
}
