package com.example.pharmapulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "feedback_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
	@Id
	private Long sno;
	private String email;
	private String drugname;
	private String cond;
	private String feedback;
	private Integer rate;
	private String sentiment;
}
