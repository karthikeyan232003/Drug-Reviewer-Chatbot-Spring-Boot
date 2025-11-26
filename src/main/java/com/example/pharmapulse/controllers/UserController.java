package com.example.pharmapulse.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.pharmapulse.interfaces.User;
import com.example.pharmapulse.models.Login_Details;
import com.example.pharmapulse.services.UsersService;


@Controller
public class UserController {
	@Autowired
	private UsersService usersService;
	
	@PostMapping("/index")
	public String validateDetails(@ModelAttribute Login_Details details,Model model)
	{
		
		User user = usersService;
		if(user.check(details.getEmail(),details.getPassword()))
		{
			Map<String,String> map = usersService.fetchUser(details.getEmail());
			model.addAllAttributes(map);
			return "chatbot";
		}
		else  
		{
			model.addAttribute("msg","Invalid login credentials");
		}
		return "login";
		
	}
	
	@PostMapping("/register")
	public String doRegister(@RequestBody Map<String, String> details)
	{
		usersService.addUser(details);
		return "chatbot";
	}
	
	
	
	  
	
	
}
