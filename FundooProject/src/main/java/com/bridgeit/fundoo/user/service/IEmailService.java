package com.bridgeit.fundoo.user.service;


import com.bridgeit.fundoo.user.dto.LoginDto;

public interface IEmailService {
	

	public void verify(LoginDto logindto);

	public void setPassword(LoginDto logindto, String token);
}
