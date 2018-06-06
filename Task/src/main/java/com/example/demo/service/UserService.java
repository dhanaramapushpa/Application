package com.example.demo.service;                                                         
                                                                                          
                                                                                          
import java.util.List;

import com.example.demo.dto.PreferenceDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Preference;
import com.example.demo.model.UserProfile;
import com.example.demo.util.Response;                                                    
                                                                                          
public interface UserService {                                                            
                                                                 
                                                                                          
  Response retrieveByEmail(String email);                                     
                                                                                    
	 Response saveUser(UserDto userDto,List<PreferenceDTO> preferenceDto);                                                   
                                                                                          
	 Response retrieveByEmailAndPassword(String email, String password);

	 String updateUser(String email,UserDto userDto);

	 Response deleteUser(String email);

	Response login(String email, String password);

	Response suggestUser(/*List<Preference> preferences, */String city);       
	
	         
	                                                                                      
}  
                                                                                          
