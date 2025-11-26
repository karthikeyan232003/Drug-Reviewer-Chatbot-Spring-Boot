package com.example.pharmapulse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEntity {
	private String email;
	private String phone_number;
	private String old_password;
	private String new_password;
	private String confirm_password;

}
