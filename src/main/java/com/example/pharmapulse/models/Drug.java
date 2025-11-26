package com.example.pharmapulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "drug_details")
@Data
@AllArgsConstructor
public class Drug {
	@Id
	private Long drug_id;
	private String drugname;
	private String cond;
	private String sentiment;
	private Integer rating;
}
