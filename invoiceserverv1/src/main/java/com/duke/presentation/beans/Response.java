package com.duke.presentation.beans;

public class Response {

	public static String SUCCESS = "Success";
	public static String FAILED = "Failed";
	
	
	public String result;
	public String code;
	public String description;
	public Object resultData;
	
	public Object getResultData() {
		return resultData;
	}
	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
