package com.duke.service.exception;

public class EmailAlreadyExistsException extends Exception{

	 private String emailId;
	 private Exception rootException;
	 
	 public EmailAlreadyExistsException(String emailId) {
		this.emailId = emailId;
	 }
	 
	 public String getMessage() {
           return  emailId + " already exists.";
	 }
}
