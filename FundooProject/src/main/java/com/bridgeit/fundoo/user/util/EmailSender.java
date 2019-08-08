package com.bridgeit.fundoo.user.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgeit.fundoo.user.dto.MailDto;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	
	public void sendEmail(MailDto mailDto) {

		Properties properties = System.getProperties();

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(mailDto.getEmail());
		msg.setSubject(mailDto.getSubject());
		msg.setText(mailDto.getBody());

		javaMailSender.send(msg);

	}

}
