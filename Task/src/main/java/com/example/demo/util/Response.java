package com.example.demo.util;

import java.util.List;

import com.example.demo.model.UserProfile;

public class Response {

	public Integer statusCode;
	
	public String message;
	
	/*public List<Object> responseObjectList;
*/
	private Object responseObject;

		
	public Integer getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	

	public Object getResponseObject() {
		return responseObject;
	}


	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}


	public Response(){
		this.message = "FAILURE";
		this.statusCode = 500;
	}
	
	public Response(String message, Integer statusCode,Object responseObject) {
		this.message = message;
		this.statusCode = statusCode;
		
		this.responseObject=responseObject;
	}


	/*public Response( String message,Integer statusCode, List<Object> responseObjectList) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.responseObjectList = responseObjectList;
	}*/


	
	
}