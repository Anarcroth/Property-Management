package com.property.manager.services;

import com.property.manager.services.impl.EmailService;

public interface IEmailService {


    void sendApprovalMessage(String email);

    void sendDeclineMessage(String email);
}
