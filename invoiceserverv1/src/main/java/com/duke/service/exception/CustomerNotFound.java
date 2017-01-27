package com.duke.service.exception;

public class CustomerNotFound extends Exception{

	private String email;
	private String fname;
	private String lname;
	private String id;
	public CustomerNotFound(String id) {
		this.id = id;
	}
	
	public CustomerNotFound(String fname, String lname, String email) {
		this.email = email;
		this.lname = lname;
		this.fname = fname;
	}
	
	public String getMessage() {
		String message  = "";
		if (id != null) {
			message = "customer does not exists with  id : " + id;

		}
		if (email != null) {
			message = "customer does not exists with email id : " + email;
		}else 
		if (fname != null) {
			message = "customer does not exists with First name id : " + fname;
		}else
		if (lname != null) {
			message = "customer does not exists with Last name id : " + lname;
		}
		
		return message;
	}
}
