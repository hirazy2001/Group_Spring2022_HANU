package com.example.group_hanu_spring2022.config;

import lombok.Data;
import lombok.Value;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

@Component
public class JwtTokenProvider {


    private int jwtExpirationInMs;

//    public String generateToken(Authentication authentication){
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expireDate = new Date(now.getTime() + jwtExpirationInMs);
//
//
//    }
//
//    public Long getUserIdFromJWT(String token){
//    }
//
//    public boolean validateToken(String authToken){
//        try{
//        }
//    }
}
