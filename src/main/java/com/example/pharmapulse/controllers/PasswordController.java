package com.example.pharmapulse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pharmapulse.interfaces.User;
import com.example.pharmapulse.models.Login_Details;
import com.example.pharmapulse.models.Password;
import com.example.pharmapulse.services.EmailService;
import com.example.pharmapulse.services.OtpService;
import com.example.pharmapulse.services.UsersService;


@Controller
public class PasswordController {
	
	@Autowired
	private OtpService otpService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UsersService usersService;
	@GetMapping("/forgot_password")
	public String forgot_password()
	{
		return "forget_password";
	}
	
	//Route for generating one time password
	@PostMapping("/gop")
	public String generateOtp(@RequestParam("email") String email, Model model)
	{
		int otp_value = otpService.generateOtp();
		boolean status = emailService.sendEmail(email, "Otp For Pharmapulse","Your Otp to Reset Password in Pharmapulse is "+otp_value);
		if(status == true)
			return "send_otp";
		else 
		{
			model.addAttribute("msg","Please enter an valid email");
			return "forget_password";
		}
	}
	
	//route for verifying one time password
	@PostMapping("/v_otp")
	public String verify_otp(@RequestParam("otp") Integer otp,Model model)
	{
		if(otpService.otpMatches(otp, otpService.getOtp()))
		{
			return "reset_password";
		}
		else
		{
			model.addAttribute("msg","Incorrect otp Entered!");
			return "send_otp";
		}
	}
	
	//function to reset password
	@PostMapping("/reset_password")
	public String password_reset(@ModelAttribute Password passwd,Model model)
	{
		if(passwd.getPassword().equals(passwd.getConfirm_password()))
		{
			String email = emailService.getEmail();
			String password = passwd.getPassword();
			User user = usersService;
			user.updatePassword(new Login_Details(email,password));
			return "login";
		}
		model.addAttribute("msg","Passwords do not match!");
		return "reset_password";
	}
	

}
