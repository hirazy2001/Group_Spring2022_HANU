package com.example.group_hanu_spring2022.repository;

import com.example.group_hanu_spring2022.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query("select a from PasswordResetToken a where a.user.id = :id")
    Optional<PasswordResetToken> findPasswordResetTokenByUser_Id(@Param("id") Long id);

    @Query("select a from PasswordResetToken a where a.token = :token")
    Optional<PasswordResetToken> findPasswordResetTokenByToken(@Param("token") String token);
}
