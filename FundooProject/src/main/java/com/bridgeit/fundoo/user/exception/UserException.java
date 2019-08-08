package com.bridgeit.fundoo.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	String msg;

	public UserException(int code, String msg) {
		super(msg);
		this.code = code;

	}

	public UserException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		return "UserException [code=" + code + ", msg=" + msg + "]";
	}

}
