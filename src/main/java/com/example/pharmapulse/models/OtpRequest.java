package com.example.pharmapulse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequest {
	private String email;
	private Integer otp;

}
