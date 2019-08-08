package com.bridgeit.fundoo.user.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgeit.fundoo.user.dto.MailDto;
import com.bridgeit.fundoo.user.util.EmailSender;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QueueConsumer {

	@Autowired
	private EmailSender emailSender;

	public void receiveMessage(String message) {
		processMessage(message);
	}

	public void receiveMessage(byte[] message) {
		String strMessage = new String(message);
		processMessage(strMessage);
	}

	private void processMessage(String message) {
		try {
			MailDto mailDTO = new ObjectMapper().readValue(message, MailDto.class);
			emailSender.sendEmail(mailDTO);
		} catch (JsonParseException e) {
			System.out.println("Bad JSON in message: " + message);
		} catch (JsonMappingException e) {
			System.out.println("cannot map JSON to NotificationRequest: " + message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
