package com.challenge.productms.errorHandling;

public class RequestErrorResponse {

	private int status_code;
	
	private String message;

	public RequestErrorResponse() {
		super();
	}

	public RequestErrorResponse(int status_code, String message) {
		super();
		this.status_code = status_code;
		this.message = message;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
