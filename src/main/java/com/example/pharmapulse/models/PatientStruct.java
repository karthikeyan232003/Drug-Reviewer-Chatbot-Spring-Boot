package com.example.pharmapulse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientStruct {
	private String email;
	private User_Registration user_details;
	private SurveyReport report;
}
