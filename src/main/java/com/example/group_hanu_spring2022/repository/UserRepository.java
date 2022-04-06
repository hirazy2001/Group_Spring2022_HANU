package com.example.group_hanu_spring2022.repository;

import com.example.group_hanu_spring2022.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select a from User a where a.username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);

    @Query("select a from User a where a.phoneNumber = :phoneNumber")
    Optional<User> findUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select a from User a where a.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("select a from User a where a.username = :username and a.phoneNumber = :phoneNumber and a.email = :email")
    Optional<User> findUserByUsernameAndPhoneNumberAndEmail(
            @Param("username") String username,
            @Param("phoneNumber") String phoneNumber,
            @Param("email") String email
            );

}
