package com.property.manager.services.impl;

import com.property.manager.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public void EmailService(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendApprovalMessage(String email) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("prop.mgmt.cos315@gmail.com");
        mail.setSubject("Property Offer");
        mail.setText("Congratulations! Your offer has been approved!");

        javaMailSender.send(mail);

    }
    @Override
    public void sendDeclineMessage(String email) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("prop.mgmt.cos315@gmail.com");
        mail.setSubject("Property Offer");
        mail.setText("Unfortunately, your offer has not been approved.");

        javaMailSender.send(mail);

    }
}
