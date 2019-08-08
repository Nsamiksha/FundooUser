package com.bridgeit.fundoo.user.config;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private int status;
	private String message;
	private Object result;

	public Response(int status, String message, Object result) 
	{
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public Response() {

	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getResult() {
		return result;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
