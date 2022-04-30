package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.dto.JWTResponseDto;
import com.example.group_hanu_spring2022.dto.LoginDto;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
import com.example.group_hanu_spring2022.model.User;
import com.example.group_hanu_spring2022.repository.UserRepository;
import com.example.group_hanu_spring2022.service.AuthenticationService;
import com.example.group_hanu_spring2022.service.UserService;
import com.example.group_hanu_spring2022.service.impl.UserDetailsImpl;
import com.example.group_hanu_spring2022.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;

    /**
     * @api {get} /accounts/:id Get Account By ID
     * @apiName DeleteUser
     * @apiGroup User
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 401 [Username does not exist, Password not correct].
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        Optional<User> userOptional = userRepository.findUserByUsername(loginDto.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorMessage("Username does not exist!"));
        }

        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new ErrorMessage("Password not correct!"));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JWTResponseDto(
                userDetails.getId(),
                "Bearer",
                userDetails.getUsername(),
                roles,
                jwt));

//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
//            );
//        } catch (Exception ex) {
//            return ResponseEntity.status(401).body(new ResourceNotFoundException("User", "Authentication", "Failed").getErrorMessage());
//        }
//
//        return ResponseEntity.ok(jwtUtil.generateToken(loginDto.getUsername()));
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
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(){

        return ResponseEntity.status(404).body(null);
    }
}
