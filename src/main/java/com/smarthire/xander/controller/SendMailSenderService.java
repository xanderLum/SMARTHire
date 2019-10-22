package com.smarthire.xander.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendMailSenderService {
	@Autowired
	private MailSender mailSender; // MailSender interface defines a strategy
										// for sending simple mails
 
	public void sendMail(String toAddress, String fromAddress, String subject, String msgBody) {
		SimpleMailMessage sendMailMessage = new SimpleMailMessage();
		sendMailMessage.setFrom(fromAddress);
		sendMailMessage.setTo(toAddress);
		sendMailMessage.setSubject(subject);
		sendMailMessage.setText(msgBody);
		mailSender.send(sendMailMessage);
	}
}
