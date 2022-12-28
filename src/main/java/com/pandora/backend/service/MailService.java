package com.pandora.backend.service;

import com.pandora.backend.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    public Email sendEmail(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email.getTo());
        message.setText(email.getBody());
        message.setSubject(email.getSubject());

        email.setFrom(from);
        sender.send(message);

        return email;
    }
}
