package com.example.pharmapulse.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmapulse.interfaces.Patient;
import com.example.pharmapulse.models.Feedback;
import com.example.pharmapulse.models.PatientStruct;
import com.example.pharmapulse.models.SurveyReport;
import com.example.pharmapulse.models.User_Registration;
import com.example.pharmapulse.repositories.RegisterRepository;
import com.example.pharmapulse.repositories.SurveyRepository;

import jakarta.mail.Message;

@Service
public class PatientService{
	
	@Autowired
	private RegisterRepository regRepository;
	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private EmailService emailService;
	public PatientStruct getPatientDetails(String email)
	{
	    User_Registration registration_details = regRepository.findById(email).get();
	    SurveyReport report = surveyRepository.findByEmail(email);
	    return new PatientStruct(email, registration_details, report);
	}
	
	public void addSurvey(SurveyReport report)
	{
		surveyRepository.save(report);
	}
	public void addSurveyReport(Map<String, String> survey)
	{
		String email = emailService.getEmail();
		String bp = survey.get("bp");
		String bpd = survey.get("bpd");
		String heart = survey.get("heart");
		String diabetes = survey.get("diabetes");
		String dpd = survey.get("dpd");
		String hpd = survey.get("hpd");
		String alcohol = survey.get("alcohol");
		String disease = survey.get("disease");
		SurveyReport report = new SurveyReport(email, bp, heart, diabetes, alcohol, bpd, hpd, dpd, disease);
		addSurvey(report);
	}

}
