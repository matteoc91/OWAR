package com.sscsweb.owar.services;

import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.dao.UserDAO;
import com.sscsweb.owar.sessionBean.UserBean;
import com.sscsweb.owar.utilities.Chiper;
import com.sscsweb.owar.utilities.Constant;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MailService mailService;
	@Autowired
	@SessionScoped
	private UserBean userBean;
	@Autowired
	private ServletContext servletContext;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ResponseMessage login(User user) {
		ResponseMessage reponseMessage = this.userDAO.login(user);
		this.userBean.setUser((User) reponseMessage.getResponseObject());
		return reponseMessage;
	}

	public int register(User user) {
		ResponseMessage responseMessage = this.userDAO.getUserFromEmail(user.getMail());
		if(responseMessage.getResponseCode() != ResponseCode.SUCCESS) {
			String body = "Hello, we are happy to meet you. Please follow this link to complete your registration: ";
			String token = Chiper.encryptText(user.getMail());
			String context = this.servletContext.getContextPath();
			body += "http://localhost:8080" + context + "/user/validate?token=" + token;
			boolean result = this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{user.getMail()}, body, Constant.EMAIL_SUBJECT_REGISTRATION);
			if(result) {
				return this.userDAO.registration(user);
			} else {
				return ResponseCode.MAIL_SEND_FAILURE;
			}
		}
		return ResponseCode.ALREADY_EXIST;
	}
	
	public int logout() {
		this.userBean.setUser(null);
		return ResponseCode.SUCCESS;
	}
	
	public int validate(String email) {
		String plainMail = Chiper.decryptText(email);
		return this.userDAO.validate(plainMail);
	}
	
	public ResponseMessage socialLogin(User user) {
		ResponseMessage responseMessage = this.userDAO.socialLogin(user);
		this.userBean.setUser((User) responseMessage.getResponseObject());
		return responseMessage;
	}

}
