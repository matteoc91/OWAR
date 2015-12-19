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

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	@RequestMapping(value = "/registration")
	public int userRegistration(@RequestParam("user") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			return this.userDAO.registration(user);
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return -1;
	}
	
}
