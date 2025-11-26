package com.example.pharmapulse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/")
	public String welcome()
	{
		
		return "login";		
	}
	
	@GetMapping("/register_page")
	public String register_page()
	{
		return "index";
	}
	@GetMapping("/guest")
    public String go_as_guest(Model model)
    {
    	model.addAttribute("is_guest",1);
    	model.addAttribute("is_check",1);
    	return "chatbot";
    }
	@GetMapping("/renderIndexPage")
	public String emailExists(Model model)
	{
		model.addAttribute("msg","Email already exists");
		return "index";
    }
	
	@GetMapping("/loginOpen")
	public String openLogin()
	{
		return "login";
	}
	@GetMapping("/logout")
	public String do_logout()
	{
		return "login";
	}
	
	
} 
