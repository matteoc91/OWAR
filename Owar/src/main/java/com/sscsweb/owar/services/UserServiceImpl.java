package com.sscsweb.owar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.dao.UserDAO;
import com.sscsweb.owar.sessionBean.UserBean;
import com.sscsweb.owar.utilities.Constant;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserBean userBean;
	
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

	public boolean login(User user) {
		int loginIsOK = this.userDAO.login(user);
		return loginIsOK == 1 ? true : false;
	}

	public boolean register(User user) {
		String body = "This is the registration mail.";
		boolean result = this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{user.getMail()}, body, Constant.EMAIL_SUBJECT_REGISTRATION);
		int registrationIsOK = -1;
		if(result) {
			registrationIsOK = this.userDAO.registration(user);
		}
		return registrationIsOK == 1 ? true : false;
	}
	
	public boolean logout() {
		this.userBean.setUser(null);
		return true;
	}

}
