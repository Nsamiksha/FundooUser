package com.bridgeit.fundoo.user.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgeit.fundoo.user.config.Response;
import com.bridgeit.fundoo.user.dto.LoginDto;
import com.bridgeit.fundoo.user.dto.UserDto;
import com.bridgeit.fundoo.user.model.User;
import com.bridgeit.fundoo.user.service.IEmailService;
import com.bridgeit.fundoo.user.service.IService;
//@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/user")
public class Controller {

	@Autowired
	private IService service;

	@Autowired
	private IEmailService emailService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public Response addUserDto(@RequestBody @Valid UserDto userdto) {
		System.out.println(userdto);
		return service.register(userdto);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Response addUserDto(@RequestBody @Valid LoginDto logindto) {
		System.out.println(logindto);

		return service.login(logindto);
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public void processForgotPassword(@RequestBody LoginDto logindto) {
		System.out.println(logindto);
		emailService.verify(logindto);
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public void processForgotPassword(@RequestBody LoginDto logindto, @RequestHeader String token) {
		System.out.println(logindto);
		emailService.setPassword(logindto, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getall")
	public List<User> addUserDto() {

		List<User> user = service.getAll();
		return user;
	}

}
