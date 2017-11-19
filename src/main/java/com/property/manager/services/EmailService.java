package com.property.manager.services;

import com.property.manager.models.User;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(User user){

    }
}
