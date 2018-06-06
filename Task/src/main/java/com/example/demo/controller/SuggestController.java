package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Preference;
import com.example.demo.service.SuggestService;
import com.example.demo.util.*;

@RestController
public class SuggestController {
	
	@Autowired
	private SuggestService suggestService;

	@RequestMapping(value="/suggestByAddress")
	public Response suggest(@RequestParam String email) {
		return suggestService.suggestByAddress(email);
	}
	
	
	@RequestMapping("/suggestByPreferences")
	public Response suggestByPreference(@RequestParam String email) {
		return suggestService.suggestByPref(email);
	}
}
