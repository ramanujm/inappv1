package com.duke.service.exception;

public class UserNotFoundException extends Exception{

	private String email;
	
	public UserNotFoundException(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return email + " does not exists.";
	}
}
