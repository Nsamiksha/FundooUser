package com.bridgeit.fundoo.user.service;

import java.util.List;

import org.springframework.context.annotation.PropertySource;
import com.bridgeit.fundoo.user.config.Response;
import com.bridgeit.fundoo.user.dto.LoginDto;
import com.bridgeit.fundoo.user.dto.UserDto;
import com.bridgeit.fundoo.user.model.User;

@PropertySource("classpath:message.properties")
public interface IService {

	Response register(UserDto userdto);

	Response login(LoginDto logindto);

	List<User> getAll();

	boolean validateUser(String email);
}
