package com.example.group_hanu_spring2022.service;

import com.example.group_hanu_spring2022.repository.PasswordResetTokenRepository;
import com.example.group_hanu_spring2022.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordResetTokenRepository resetTokenRepository;


    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }

    public void sendEmailResetPassword(
            String fullName,
            String token,
            String toEmail
    ) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<h3>Hello World!</h3>";

        String content = "" +
                "Hey, " + fullName + ".<br><br>\n" +
                "        You requested a new password for your Server Chatapp account.<br>\n" +
                "        Please use the following link to set a new password. It will expire in 1 hour.\n" +
                "        <br><br>\n" +
                "        Click <b>" + token + "</b> to copy to clipboard and reset password.\n" +
                "        <br><br>\n" +
                "        If you didn't make this request then you can safely ignore this email. :)<br><br>\n" +
                "        &mdash; HANU Banking System Team";
        // mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        helper.setText(content, true); // Use this or above line.
        helper.setTo(toEmail);
        helper.setSubject("HANU BANKING SYSTEM - Reset Password");
        helper.setFrom("1901040142@s.hanu.edu.vn");
        mailSender.send(mimeMessage);


    }


}
