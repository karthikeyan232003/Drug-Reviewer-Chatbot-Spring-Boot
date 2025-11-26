package com.example.pharmapulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "feedback_tracker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackTracker {
	@Id
	private String email;
	private String date_of_feedback;
}
