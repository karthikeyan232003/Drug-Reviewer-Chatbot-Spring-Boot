package com.example.pharmapulse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmapulse.models.SurveyReport;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyReport,String> {
	SurveyReport findByEmail(String email);

}
