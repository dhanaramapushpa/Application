package com.example.demo.validator;

import org.springframework.validation.Errors;

public interface validator {

	boolean supports(Class<?> arg0);

	void validate(Object arg0, Errors arg1);

}
