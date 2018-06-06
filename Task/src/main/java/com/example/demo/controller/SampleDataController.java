package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PreferenceService;
import com.example.demo.util.Response;

@RestController
public class SampleDataController {

	@Autowired PreferenceService preferenceService;
	
	@RequestMapping(value = "/insert-sample-data", method = RequestMethod.POST)
	public Response InsertSampleData() {
		return preferenceService.CreateSampleData();
	}
	
}


