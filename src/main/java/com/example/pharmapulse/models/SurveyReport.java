package com.example.pharmapulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "survey_report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyReport {
	@Id
	private String email;
	private String bp;
	private String heart;
	private String diabetes;
	private String alcohol;
	private String bp_drug;
	private String heart_drug;
	private String diabetes_drug;
	private String disease;
}
