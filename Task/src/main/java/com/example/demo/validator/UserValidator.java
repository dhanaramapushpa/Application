package com.example.demo.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.demo.dto.PreferenceDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Response;

@Component
public class UserValidator implements validator{
	
	@Autowired 
	private UserRepository userRepo;
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public Response signUpValidator(UserDto dto,List<PreferenceDTO> preferenceDto) {
		Response response=null;
		
	   List<UserProfile> userProfiles=userRepo.findAll();
		List<String> savedEmail=new ArrayList<>();
		for(UserProfile u:userProfiles) {
		     String email=u.getEmail();
		     savedEmail.add(email); 
		}

		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		
		Pattern pattern = Pattern.compile(regex);
		
	   Matcher matcher = pattern.matcher(dto.getEmail());
	     
		   if(matcher.matches()==false) {
			   return new Response("Invalid email id",500,"Failure");
		   }
		
		if(savedEmail.contains(dto.getEmail())) {
			return new Response("email id already exists",500,"Failure");
		}

		if(dto.getName().length()<4||dto.getName().length()>25) {
			return new Response("name should contain atleast 4 characters and maximum 25 characters",500,"Failure");
		}
		
		 if(dto.getPassword().length()<6&&!dto.getPassword().contains("0,1,2,3,4,5,6,7,8,9")) {
			return new Response("password should contain atleast 6 alphabets with numbers",500,"Failure");
		}
		
		if(dto.getUserAddress()==null) {
			return new Response("atleast you should have one address for the user",500,"Failure");
		}
		
		if(preferenceDto==null) {
			return new Response("atleast you should have one preference for the user",500,"Failure");
		}
	
		return response;
	}
}
