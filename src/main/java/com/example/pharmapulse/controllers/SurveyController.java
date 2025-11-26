package com.example.pharmapulse.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pharmapulse.services.EmailService;
import com.example.pharmapulse.services.PatientService;
import com.example.pharmapulse.services.UsersService;

@Controller
public class SurveyController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PatientService patientService;
	
	
	@GetMapping("/questionsOpen")
	public String openSurvey()
	{
		
		return "questions";
	}
	
	@PostMapping("/questions")
	public String getQuestionsSurvey(@RequestBody Map<String, String> request,Model model)
	{		
		//System.out.println(request+" "+emailService.getEmail());
		System.out.println("Question responses: "+request);
		patientService.addSurveyReport(request);
		return "index";
		
	}
	
	@PostMapping("/med")
	public String medicalUpdate(@RequestParam("clicked") String clicked,Model model)
	{
		if(clicked.equals("No"))
		{
			model.addAttribute("is_guest",1);
			model.addAttribute("is_check",0);
			String email = emailService.getEmail();
			HashMap<String, String> patient = usersService.fetchUser(email);
			model.addAllAttributes(patient);
			return "chatbot";
		}
		return "questions";
	}

}
