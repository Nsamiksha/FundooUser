
package com.bridgeit.fundoo.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoo.user.config.Response;
import com.bridgeit.fundoo.user.dto.LoginDto;
import com.bridgeit.fundoo.user.dto.MailDto;
import com.bridgeit.fundoo.user.model.User;
import com.bridgeit.fundoo.user.rabbitmq.QueueProducer;
import com.bridgeit.fundoo.user.repository.UserRepository;
import com.bridgeit.fundoo.user.util.EmailSender;
import com.bridgeit.fundoo.user.util.TokenGenerator;

@PropertySource("classpath:application.properties")
@Service
public class EmailServiceImpl implements IEmailService {
	

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Response statusResponse;

	@Autowired
	private Environment environment;

	@Autowired
	private QueueProducer queueProducer;

	@Value("${Email}")
	private static String fromEmail;

	@Value("${password}")
	private static String password;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public void verify(LoginDto loginDto) {
		Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
		if (user.isEmpty()) {
			throw new RuntimeException("Email ID does not exist.");
		} else {

			String token = tokenGenerator.createToken(user.get().getId());
			String text = "http://localhost:8080/reset";

			MailDto mailDto = new MailDto();
			mailDto.setBody(text + token);
			mailDto.setSubject("link");
			mailDto.setEmail(loginDto.getEmail());

			try {
				queueProducer.produce(mailDto);
				emailSender.sendEmail(mailDto);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void setPassword(LoginDto loginDto, String token) {

		long id = tokenGenerator.decryptToken(token);

		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new RuntimeException("Invalid user");
		} else {
			String password = passwordEncoder.encode(loginDto.getPassword());
			user.get().setPassword(password);

			userRepository.save(user.get());
		}

	}
}
