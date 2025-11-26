package com.example.pharmapulse.interfaces;

import com.example.pharmapulse.models.Login_Details;
import com.example.pharmapulse.models.User_Registration;

public interface User {
	boolean check(String email,String password);
	void addUser(User_Registration user);
	void updatePassword(Login_Details details);
	void updateDetails(User_Registration registration);
	Login_Details findUser(String email);
	User_Registration fetchRegistrationDetails(String email);
}
