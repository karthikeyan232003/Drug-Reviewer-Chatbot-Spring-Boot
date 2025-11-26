package com.example.pharmapulse.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmapulse.interfaces.User;
import com.example.pharmapulse.models.Login_Details;
import com.example.pharmapulse.models.PatientStruct;
import com.example.pharmapulse.models.User_Registration;
import com.example.pharmapulse.repositories.RegisterRepository;
import com.example.pharmapulse.repositories.UsersRepository;

@Service
public class UsersService implements User{
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private RegisterRepository userRegisterRepository;
	
	@Autowired 
	private PatientService patientService;
	
	@Autowired
	private EmailService emailService;
	
	public boolean check(String email,String password)
	{
		Optional<Login_Details> user = userRepository.findByEmail(email);
		if(user.isEmpty() || user.get().getEmail().equals(email)==false || user.get().getPassword().equals(password)==false)
		{
			return false;
		}
		return true;
	}
	
	
	
	
	public HashMap<String, String> fetchUser(String email)
	{
		PatientStruct patient = patientService.getPatientDetails(email);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", patient.getUser_details().getName());
		map.put("email",email);
		map.put("phone_number",""+patient.getUser_details().getPhoneNumber());
		map.put("bp",patient.getReport().getBp_drug());
		map.put("heart", patient.getReport().getHeart_drug());
		map.put("diabetes",patient.getReport().getDiabetes_drug());
		map.put("other",patient.getReport().getDisease());
		return map;
	}
	public void addUser(User_Registration user)
	{
		
		userRegisterRepository.save(user);
		
	}
	public void addCredentials(Login_Details login)
	{
		userRepository.save(login);
	}
	
	public void addUser(Map<String,String> map)
	{
		String name = map.get("fullName");
		String dob = map.get("dob");
		Long phoneNumber = Long.parseLong(map.get("phoneNumber"));
		String gender = map.get("gender");
	    int age = Integer.parseInt(map.get("age"));
	    String email = emailService.getEmail();
	    String password = map.get("password");
	    String regDate = ""+LocalDate.now();
	    Login_Details login = new Login_Details(email, password);
	    User_Registration user = new User_Registration(email, name, dob, phoneNumber, gender, age, regDate);
	    System.out.println(user);
	    addUser(user);
	    addCredentials(login);
	}
	
	public void updatePassword(Login_Details details)
	{
		userRepository.save(details);
	}
	
	public void updateDetails(User_Registration details)
	{
		userRegisterRepository.save(details);
	}

	
	public Login_Details findUser(String email) {
		Optional<Login_Details> login = userRepository.findByEmail(email);
		return login.get();
		
	}
	
	public User_Registration fetchRegistrationDetails(String email)
	{
		User_Registration user = userRegisterRepository.findByEmail(email);
		return user;
	}
	

}
