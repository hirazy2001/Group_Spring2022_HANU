package com.example.group_hanu_spring2022.service.impl;

import com.example.group_hanu_spring2022.dto.PasswordResetDto;
import com.example.group_hanu_spring2022.dto.ResetDto;
import com.example.group_hanu_spring2022.dto.UserInfoDto;
import com.example.group_hanu_spring2022.model.PasswordResetToken;
import com.example.group_hanu_spring2022.model.User;
import com.example.group_hanu_spring2022.repository.PasswordResetTokenRepository;
import com.example.group_hanu_spring2022.repository.UserRepository;
import com.example.group_hanu_spring2022.service.EmailSenderService;
import com.example.group_hanu_spring2022.service.PasswordResetService;
import com.example.group_hanu_spring2022.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    ServiceHelper serviceHelper;

    @Autowired
    EmailSenderService emailSenderService;

    Logger logger = LoggerFactory.getLogger(PasswordResetServiceImpl.class);

    @Override
    public PasswordResetToken create(PasswordResetDto passwordResetDto) {

        Optional<User> userOptional = userRepository.findUserByUsernameAndPhoneNumberAndEmail(
                passwordResetDto.getUsername(),
                passwordResetDto.getPhoneNumber(),
                passwordResetDto.getEmail());

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        String fullName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
        String token = Utility.getAlphaNumericString(24);

        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.
                findPasswordResetTokenByUser_Id(user.getId());

        // Exist PasswordReset Entity
        if (!passwordResetToken.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.now();

            logger.error("now " + localDateTime.toString() + "" +
                    "\ndto " + passwordResetToken.get().getExpiryDate().toString());

            // Out of date => Delete
            if (localDateTime.isAfter(passwordResetToken.get().getExpiryDate())) {
                // Delete
                passwordResetTokenRepository.deleteById(passwordResetToken.get().getId());

                // send Email
                try {
                    emailSenderService.sendEmailResetPassword(fullName, token, user.getEmail());
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return null;
                }

                PasswordResetToken passwordResetTokenDto = new PasswordResetToken(token, user);

                PasswordResetToken newPasswordReset =
                        passwordResetTokenRepository.save(passwordResetTokenDto);

                return newPasswordReset;
            } else {

                return passwordResetToken.get();
            }
        }

        // send Email
        try {
            emailSenderService.sendEmailResetPassword(fullName, token, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }

        PasswordResetToken passwordResetTokenDto = new PasswordResetToken(token, user);

        PasswordResetToken newPasswordReset =
                passwordResetTokenRepository.save(passwordResetTokenDto);

        return newPasswordReset;
    }

    @Override
    public UserInfoDto resetPassword(ResetDto resetDto) {

        Optional<PasswordResetToken> newPasswordReset =
                passwordResetTokenRepository.findPasswordResetTokenByToken(resetDto.getToken());

        if (newPasswordReset.isEmpty()) {
            return null;
        }

        PasswordResetToken passwordResetToken = newPasswordReset.get();

        LocalDateTime localDateTime = LocalDateTime.now();

        if (passwordResetToken.getExpiryDate().isBefore(localDateTime)) {
            return null;
        }

        User user = passwordResetToken.getUser();

        // Update Password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(resetDto.getPassword());
        user.setPassword(encodedPassword);

        // Save User
        User updatedUser = userRepository.save(user);

        // Remove PasswordResetToken
        passwordResetTokenRepository.deleteById(passwordResetToken.getId());

        return serviceHelper.convertToUserInfoDto(updatedUser);
    }

}
