package com.example.pharmapulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table(name = "recommend_details")
@Data
@AllArgsConstructor
public class Recommend {
	@Id
	private String email;
	private Integer recommend;
}
