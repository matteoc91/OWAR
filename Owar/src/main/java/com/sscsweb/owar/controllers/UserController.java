package com.sscsweb.owar.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.dao.UserDAO;
import com.sscsweb.owar.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserService userService;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/registration")
	public int userRegistration(@RequestParam("user") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			return this.userService.register(user) ? 1 : -1;
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return -1;
	}
	
	@RequestMapping(value = "/login")
	public int userLogin(@RequestParam("user") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			return this.userService.login(user) ? 1 : -1;
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return -1;
	}
	
	@RequestMapping(value = "/logout")
	public int userLogout() {
		return this.userService.logout() ? 1 : -1;
	}
	
}
