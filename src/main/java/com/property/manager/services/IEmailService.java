package com.property.manager.services;

public interface IEmailService {

	void sendApprovalMessage(String email);

	void sendDeclineMessage(String email);
}
