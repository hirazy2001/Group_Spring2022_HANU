package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.dto.JWTResponseDto;
import com.example.group_hanu_spring2022.dto.LoginDto;
import com.example.group_hanu_spring2022.service.AuthenticationService;
import com.example.group_hanu_spring2022.service.impl.UserDetailsImpl;
import com.example.group_hanu_spring2022.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
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
                userDetails.getPassword(),
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
}
