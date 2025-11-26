package com.example.pharmapulse.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
	@Autowired
	private EmailService emailService;
	private Integer otp;
	private String formatOtp(int otp)
	{
		
		return "Your otp for pharmapulse is "+otp;
	}
	public boolean otpMatches(int otp1,int otp2)
	{
		return otp1==otp2;
	}
	public void otpSend(String email,int otp)
	{
		
		String message = formatOtp(otp);
		emailService.sendEmail(email, "Otp For Pharmapulse", message);
	}
	
	public Integer generateOtp()
	{
		int max_limit = 99999;
		int min_limit = 10000;
		int random_number = new Random().nextInt(max_limit-min_limit+1) + min_limit;
		setOtp(random_number);
		return random_number;
	}
	
	public void setOtp(int otp)
	{
		this.otp = otp;
	}
	
	public Integer getOtp()
	{
		return otp;
	}

}
