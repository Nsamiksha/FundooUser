package com.bridgeit.fundoo.user.service;

import com.bridgeit.fundoo.user.dto.MailDto;

public interface IMailService {
	
	public void sendEmail(MailDto maildto);
}
