package com.bridgeit.fundoo.user.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgeit.fundoo.user.dto.MailDto;

@Component
public class MailServiceImpl implements IMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(MailDto maildto) {

		Properties properties = System.getProperties();

// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		try {

// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(maildto.getEmail()));

// Set Subject: header field
			message.setSubject(maildto.getSubject());

// Now set the actual message
			message.setText(maildto.getBody());

// Send message
			javaMailSender.send(message);
			System.out.println("\nMail Sent Successfully....");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
