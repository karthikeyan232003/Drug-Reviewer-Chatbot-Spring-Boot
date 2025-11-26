package com.example.pharmapulse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	private String email;
	public boolean sendEmail(String recipient_email,String subject,String message_to_be_sent)
	{
		
		email = recipient_email;
		try
		{
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("healthvision634@gmail.com");
			message.setTo(recipient_email);
			message.setSubject(subject);
			message.setText(message_to_be_sent);
			javaMailSender.send(message);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return email;
	}

}
