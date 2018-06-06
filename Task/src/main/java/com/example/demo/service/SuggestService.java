package com.example.demo.service;

import com.example.demo.util.Response;

public interface SuggestService {

	Response suggestByAddress(String email);

	Response suggestByPref(String email);

	
}
