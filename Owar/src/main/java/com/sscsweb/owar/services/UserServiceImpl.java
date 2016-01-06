package com.sscsweb.owar.services;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.dao.UserDAO;
import com.sscsweb.owar.sessionBean.UserBean;
import com.sscsweb.owar.utilities.Chiper;
import com.sscsweb.owar.utilities.Constant;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MailService mailService;
	@Autowired
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

	public boolean login(User user) {
		int loginIsOK = this.userDAO.login(user);
		return loginIsOK == 1 ? true : false;
	}

	public boolean register(User user) {
		int registrationIsOK = -1;
		if(this.userDAO.getUserFromEmail(user.getMail()) == null) {
			String body = "Hello, we are happy to meet you. Please follow this link to complete your registration: ";
			String token = Chiper.encryptText(user.getMail());
			String context = this.servletContext.getContextPath();
			body += "http://localhost:8080" + context + "/user/validate?token=" + token;
			boolean result = this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{user.getMail()}, body, Constant.EMAIL_SUBJECT_REGISTRATION);
			if(result) {
				registrationIsOK = this.userDAO.registration(user);
			}
		}
		return registrationIsOK == 1 ? true : false;
	}
	
	public boolean logout() {
		this.userBean.setUser(null);
		return true;
	}
	
	public boolean validate(String email) {
		String plainMail = Chiper.decryptText(email);
		return this.userDAO.validate(plainMail) == 1 ? true : false;
	}
	
	public boolean socialLogin(User user) {
		if(this.userDAO.socialLogin(user) == 1) {
			return true;
		}
		return false;
	}

}
