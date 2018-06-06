package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.IncomingRequestBody;
import com.example.demo.dto.PreferenceDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Preference;
import com.example.demo.model.UserProfile;
import com.example.demo.service.UserService;
import com.example.demo.util.Response;



@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	    
	@RequestMapping(value="/loginUser")
	public Response loginUser(@RequestParam String email,@RequestParam String password) {
		return userService.login(email,password);
	}
	
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public Response save(@RequestBody IncomingRequestBody requestBody) {
		return userService.saveUser(requestBody.getUserDto(),requestBody.getPreferenceDto());
	}
	
	@RequestMapping("/retrieve/{email}")
	public Response retrieve(@PathVariable String email) {
		return userService.retrieveByEmail(email);
	}
	
	@RequestMapping("/retrieveByEmailAndPassword")
	public Response retrieving(@RequestParam String email,@RequestParam String password) {
		return userService.retrieveByEmailAndPassword(email,password);
	}

	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public String update(@RequestParam String email,@RequestBody UserDto userDto) {
		return userService.updateUser(email,userDto);
	}
	
	
	@RequestMapping("/delete")
	public Response delete(@RequestParam String email) {
		return userService.deleteUser(email);
	}
	
	@RequestMapping("/suggest")
	public Response suggest(/*List<Preference> preference,*/@RequestParam String city) {
		return userService.suggestUser(/*preference,*/city);
	}
	
}
