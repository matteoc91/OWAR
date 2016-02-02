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
import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.services.UserService;
import com.sscsweb.owar.utilities.Constant;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ServletContext servletContext;
	
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
			return this.userService.register(user);
		
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
		return this.userService.logout();
	}
	
	@RequestMapping(value = "/validate")
	public void userValidation(@RequestParam("token") String token, HttpServletResponse response) {
		this.userService.validate(token);
		try {
			response.sendRedirect(Constant.DEFAULT_URL + servletContext.getContextPath() + "/validate");
		} catch (IOException e) {
			
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
	
	@RequestMapping(value = "/getAdminPrivilege")
	public ResponseMessage getAdminPrivilege() {
		return this.userService.getAdminPrivilege();
	}
	
	@RequestMapping(value = "/update")
	public ResponseMessage updateUser(@RequestParam("user") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(json, User.class);
			return this.userService.updateUser(user);
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/completeProfile")
	public ResponseMessage completeUserProfile(@RequestParam("lessor") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			Lessor lessor = mapper.readValue(json, Lessor.class);
			return this.userService.createLessor(lessor);
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/updateFeedbackTenant")
	public ResponseMessage updateFeedbackTenant(@RequestParam("feedback") double feedback, @RequestParam("tenantId") int tenantId) {
		return this.userService.updateFeedbackTenant(feedback, tenantId);
	}
	
	@RequestMapping(value = "/updateFeedbackLessor")
	public ResponseMessage updateFeedbackLessor(@RequestParam("feedback") double feedback, @RequestParam("lessorId") int lessorId) {
		return this.userService.updateFeedbackLessor(feedback, lessorId);
	}
	
}
