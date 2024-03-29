package com.bridgeit.fundoo.user.exception;

import java.util.List;

public class ErrorResponse {
	private String message;

	private List<String> details;

	public String getMessage() {
		return message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}
	public ErrorResponse() {
		
	}
}
