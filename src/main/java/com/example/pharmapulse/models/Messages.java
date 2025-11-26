package com.example.pharmapulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
	@Id
	private String email;
	private String message;
	private String response;
	private String dateOfPosting;
}
