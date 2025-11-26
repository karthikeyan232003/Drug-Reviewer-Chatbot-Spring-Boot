package com.example.pharmapulse.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmapulse.dto.UpdateEntity;
import com.example.pharmapulse.errors.ErrorResponse;
import com.example.pharmapulse.errors.SuccessResponse;
import com.example.pharmapulse.interfaces.User;
import com.example.pharmapulse.models.Login_Details;
import com.example.pharmapulse.models.OtpRequest;
import com.example.pharmapulse.models.User_Registration;
import com.example.pharmapulse.services.OtpService;
import com.example.pharmapulse.services.UsersService;

@RestController
public class ValidationController {
	   
	   @Autowired
	   private UsersService usersService;
	   @Autowired
	   private OtpService otpService;
	   
	   
	
	   @PostMapping("/doesExist")
	  public ArrayList<Integer> doesExists(@RequestBody Map<String, String> data) 
	   {
		  System.out.println(data);
	      String email = data.get("email");
        ArrayList<Integer> a1 = new ArrayList<>();
        try 
        {
      	  Login_Details log = usersService.findUser(email);
      	  //System.out.println(log.getEmail()+" "+log.getPassword());
      	  a1.add(1);
        }
        catch(Exception e)
        {
      	  a1.add(0);
        }
        System.out.println(a1);
        
        return a1;
	    }
	   @PostMapping("/otpAuthentication")
		 public List<Integer> otpAuthentication(@RequestBody OtpRequest otpRequest) {
		        String email = otpRequest.getEmail();
		        Integer otp = otpRequest.getOtp();
		        otpService.otpSend(email,otp);
		        System.out.println(email + " " + otp);
		        return Arrays.asList(1);
		    }
	    @PostMapping("/update")
		public ResponseEntity<Object> update(@ModelAttribute UpdateEntity userDetail)
		{
			 
			 String email = userDetail.getEmail(); //get the email
			 String phoneNumber = userDetail.getPhone_number(); 
			 String oldPassword = userDetail.getOld_password();
			 String newPassword = userDetail.getNew_password();
			 String confirmPassword = userDetail.getConfirm_password();
			 Login_Details user = usersService.findUser(email);
			 if(user.getPassword().equals(oldPassword)==false)
			 {
				 return ResponseEntity
		                    .status(HttpStatus.OK)
		                    .body(new ErrorResponse("Invalid Old Password"));
			 }
			 if(newPassword.length() == 0 && phoneNumber.length()==0)
			 {
				 return ResponseEntity
		                    .status(HttpStatus.OK)
		                    .body(new ErrorResponse("Please enter either mobile number or password"));
			 }
			 if(newPassword.equals(confirmPassword)==false)
			 {
				 return ResponseEntity
		                    .status(HttpStatus.BAD_REQUEST)
		                    .body(new ErrorResponse("Passwords do not match"));
			 }
			 if(newPassword.length()!=0)
				 usersService.updatePassword(new Login_Details(email,newPassword));
			 if(phoneNumber.length()!=0)
			 {
				 User user1 = usersService;
				 User_Registration uss = user1.fetchRegistrationDetails(email);
				 uss.setPhoneNumber(Long.parseLong(phoneNumber));
				 user1.updateDetails(uss);
				 
			 }
			 return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new SuccessResponse("Account updated successfully"));
		}
	   
	  

}
