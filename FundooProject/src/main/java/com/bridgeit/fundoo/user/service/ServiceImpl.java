package com.bridgeit.fundoo.user.service;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgeit.fundoo.user.config.Response;
import com.bridgeit.fundoo.user.dto.LoginDto;
import com.bridgeit.fundoo.user.dto.UserDto;
import com.bridgeit.fundoo.user.exception.RecordNotFoundException;
import com.bridgeit.fundoo.user.exception.ResponseHelper;
import com.bridgeit.fundoo.user.exception.UserException;
import com.bridgeit.fundoo.user.model.User;
import com.bridgeit.fundoo.user.repository.UserRepository;
import com.bridgeit.fundoo.user.util.Function;
import com.bridgeit.fundoo.user.util.TokenGenerator;
import com.bridgeit.fundoo.user.util.Validation;

@PropertySource("classpath:message.properties")
@Service
public class ServiceImpl implements IService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Environment environment;

	@Autowired
	private Response statusResponse;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private Function function;

	@Override
	public Response register(UserDto userDto) {

		if (!validateUser(userDto.getEmail())) {
			throw new UserException(environment.getProperty("status.register.emailExistError"));
		}
//
//		if (!validation.validateEmailAddress(userDto.getEmail())) {
//			throw new UserException(environment.getProperty("user.login.email"));
//		}

		String password = passwordEncoder.encode(userDto.getPassword());

		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDto, User.class);

		user.setDate(function.date());

		user.setPassword(password);

		userRepository.save(user);

		return statusResponse = ResponseHelper.statusResponse(200, "register successfully", userDto.getEmail());

	}

	@Override
	public Response login(LoginDto loginDto) {
		Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

		if (user.isEmpty()) {
			throw new RecordNotFoundException("Record Not Found");
		}

		if (!passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
			throw new UserException(environment.getProperty("user.login.password"));
		}

		String token = tokenGenerator.createToken(user.get().getId());
		loginDto.setEmail(token);

		statusResponse = ResponseHelper.statusResponse(200, "success", loginDto.getEmail());
		return statusResponse;
	}

	@Override
	public List<User> getAll() {

		List<User> user = userRepository.findAll();

		return user;
	}

	@Override
	public boolean validateUser(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		System.out.println(user);
		if (user.isEmpty()) {
			return true;
		}
		return false;
	}

}
