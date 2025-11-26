package com.example.pharmapulse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Registration {
	
	@Id
	private String email;
    private String name;
    private String dob;
    private Long phoneNumber;
    private String gender;
    private Integer age;
    private String registration_date;

}
