package com.example.pharmapulse.interfaces;

import com.example.pharmapulse.models.Feedback;
import com.example.pharmapulse.models.SurveyReport;

import jakarta.mail.Message;


public interface Patient {
	
	boolean hasDateCrossed(String date); 
	void updateFeedback(Feedback feedback);
	void updateMedicalHistory(SurveyReport report);
	void addSurvey(SurveyReport report);
	void saveMessage(Message message);
	
}
