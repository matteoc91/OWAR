package com.sscsweb.owar.controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

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
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private ServletContext servletContext;
	
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

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/registration")
	public ResponseMessage userRegistration(@RequestParam("user") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			int result = this.userService.register(user);
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/login")
	public ResponseMessage userLogin(@RequestParam("user") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			return this.userService.login(user);
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/logout")
	public ResponseMessage userLogout() {
		int result = this.userService.logout();
		return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
	}
	
	@RequestMapping(value = "/validate")
	public void userValidation(@RequestParam("token") String token, HttpServletResponse response) {
		this.userService.validate(token);
		try {
			response.sendRedirect("http://localhost:8080/" + servletContext.getContextPath() + "?isValidated=true");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	@RequestMapping(value = "/socialLogin")
	public ResponseMessage socialLogin(@RequestParam("user") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			return this.userService.socialLogin(user);
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
}
