package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.SuggestionDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.UserProfile;
import com.example.demo.service.SuggestService;
import com.example.demo.service.UserService;
import com.example.demo.util.Response;

@Controller
public class Contoller {
	
	
	@Autowired
	private SuggestService suggestService;

	
	
	@RequestMapping("/")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute UserProfile userProfile) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("user-data");
		modelAndView.addObject("user", userProfile);
		return modelAndView;
	}
	
	@RequestMapping("/suggestion-preferance")
	public ModelAndView retrieveSuggestionsByPreferance(@RequestParam String email) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("suggestion");
		Response response = suggestService.suggestByPref(email);
		List<UserDto> userDto = new ArrayList<>();
		if (response.getStatusCode() == 200){
			userDto = (List<UserDto>) response.getResponseObject();
			System.out.println("+++++++++++++++++"+userDto);
			if (userDto.size() > 0) {
				modelAndView.addObject("userDto", userDto);
			}
		}
		return modelAndView;
	}
	
	
	@RequestMapping("/suggestion-address")
	public ModelAndView retrieveSuggestionsByAddress(@RequestParam String email) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("suggestion");
		Response response = suggestService.suggestByAddress(email);
		List<UserDto> userDto = new ArrayList<>();
		if (response.getStatusCode() == 200){
			userDto = (List<UserDto>) response.getResponseObject();
			System.out.println("+++++++++++++++++"+userDto);
			if (userDto.size() > 0) {
				modelAndView.addObject("userDto", userDto);
			}
		}
		return modelAndView;
	}

}
