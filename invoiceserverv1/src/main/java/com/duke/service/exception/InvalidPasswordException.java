package com.duke.service.exception;


public class InvalidPasswordException extends Exception{

	private String email;
	
	public InvalidPasswordException(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return " password is invalid.";
	}
}

