package com.sscsweb.owar.services;

import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sscsweb.owar.entities.Administrator;
import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.entities.Tenant;
import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.dao.AdministratorDAO;
import com.sscsweb.owar.jdbc.dao.LessorDAO;
import com.sscsweb.owar.jdbc.dao.TenantDAO;
import com.sscsweb.owar.jdbc.dao.UserDAO;
import com.sscsweb.owar.sessionBean.UserBean;
import com.sscsweb.owar.utilities.Chiper;
import com.sscsweb.owar.utilities.Constant;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

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
	@Autowired
	private AdministratorDAO administratorDAO;
	@Autowired
	private TenantDAO tenantDAO;
	@Autowired
	private LessorDAO lessorDAO;
	
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

	public AdministratorDAO getAdministratorDAO() {
		return administratorDAO;
	}

	public void setAdministratorDAO(AdministratorDAO administratorDAO) {
		this.administratorDAO = administratorDAO;
	}

	public TenantDAO getTenantDAO() {
		return tenantDAO;
	}

	public void setTenantDAO(TenantDAO tenantDAO) {
		this.tenantDAO = tenantDAO;
	}

	public LessorDAO getLessorDAO() {
		return lessorDAO;
	}

	public void setLessorDAO(LessorDAO lessorDAO) {
		this.lessorDAO = lessorDAO;
	}

	public ResponseMessage login(User user) {
		ResponseMessage reponseMessage = this.userDAO.login(user);
		this.userBean.setUser((User) reponseMessage.getResponseObject());
		this.setAdminBean();
		this.setTenantBean();
		this.setLessorBean();
		return reponseMessage;
	}

	public ResponseMessage register(User user) {
		ResponseMessage responseMessage = this.userDAO.getUserFromEmail(user.getMail());
		if(responseMessage.getResponseCode() != ResponseCode.SUCCESS) {
			String body = "Hello, we are happy to meet you. Please follow this link to complete your registration: ";
			String token = Chiper.encryptText(user.getMail());
			String context = this.servletContext.getContextPath();
			body += Constant.DEFAULT_URL + context + "/user/validate?token=" + token;
			boolean result = this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{user.getMail()}, body, Constant.EMAIL_SUBJECT_REGISTRATION);
			if(result) {
				return this.userDAO.registration(user);
			} else {
				return new ResponseMessage(ResponseCode.MAIL_SEND_FAILURE, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.MAIL_SEND_FAILURE), null);
			}
		}
		return new ResponseMessage(ResponseCode.ALREADY_EXIST, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.ALREADY_EXIST), null);
	}
	
	public ResponseMessage logout() {
		this.userBean.setUser(null);
		this.userBean.setTenant(null);
		this.userBean.setLessor(null);
		this.userBean.setAdministrator(null);
		this.userBean.setAdminMode(false);
		this.userBean.setLessorMode(false);
		this.userBean.setLessorMode(false);
		return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), null);
	}
	
	public ResponseMessage validate(String email) {
		String plainMail = Chiper.decryptText(email);
		return this.userDAO.validate(plainMail);
	}
	
	public ResponseMessage socialLogin(User user) {
		ResponseMessage responseMessage = this.userDAO.socialLogin(user);
		this.userBean.setUser((User) responseMessage.getResponseObject());
		this.setAdminBean();
		this.setTenantBean();
		this.setLessorBean();
		return responseMessage;
	}
	
	public ResponseMessage getAdminPrivilege() {
		if(this.userBean.getAdministrator() != null) {
			this.userBean.setAdminMode(true);
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), null);
		}
		return new ResponseMessage(ResponseCode.NOT_VALID, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_VALID), null);
	}
	
	private void setAdminBean() {
		if(this.userBean.getUser() != null) {
			ResponseMessage responseMessage = this.administratorDAO.getAdministrator(this.userBean.getUser().getId());
			this.userBean.setAdministrator((Administrator) responseMessage.getResponseObject());
		}
	}
	
	private void setTenantBean() {
		if(this.userBean.checkTenant()) {
			ResponseMessage responseMessage = this.tenantDAO.getTenant(this.userBean.getUser().getId());
			this.userBean.setTenant((Tenant) responseMessage.getResponseObject());
		}
	}
	
	private void setLessorBean() {
		if(this.userBean.getTenant() != null) {
			ResponseMessage responseMessage = this.lessorDAO.getLessor(this.userBean.getUser().getId());
			this.userBean.setLessor((Lessor) responseMessage.getResponseObject());
		}
	}
	
	public ResponseMessage getTenant(Integer userId) {
		if(this.userBean.getTenant() != null) {
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), this.userBean.getTenant());
		}
		Tenant tenant = new Tenant();
		tenant.setUser_id(userId);
		ResponseMessage responseMessage = this.tenantDAO.getTenant(userId);
		if(responseMessage.getResponseCode() != ResponseCode.SUCCESS) {
			responseMessage = this.tenantDAO.createTenant(tenant);
			if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
				responseMessage = this.tenantDAO.getTenant(userId);
			}
			this.userBean.setTenant((Tenant) responseMessage.getResponseObject());
		}
		return responseMessage;
	}
	
	public ResponseMessage createTenant(Tenant tenant) {
		ResponseMessage responseMessage = this.tenantDAO.createTenant(tenant);
		this.setTenantBean();
		return responseMessage;
	}
	
	public ResponseMessage updateUser(User user) {
		ResponseMessage responseMessage = this.userDAO.update(user);
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			responseMessage = this.userDAO.getUserFromEmail(user.getMail());
			this.userBean.setUser((User) responseMessage.getResponseObject());
		}
		return responseMessage;
	}
	
	public ResponseMessage createLessor(Lessor lessor) {
		ResponseMessage responseMessage = this.lessorDAO.createLessor(lessor);
		this.setLessorBean();
		return responseMessage;
	}
	
	public ResponseMessage updateFeedbackTenant(double feedback, Integer tenantId) {
		ResponseMessage responseMessage = this.tenantDAO.getTenantFromId(tenantId);
		Tenant tenant = (Tenant) responseMessage.getResponseObject();
		Integer feedbackNumber = tenant.getFeedback_number();
		Double feedbackValue = tenant.getFeedback_tenant();
		if(feedbackNumber == null || feedbackNumber < 1) {
			feedbackNumber = 1;
			feedbackValue = feedback;
		} else {
			feedbackValue = (feedbackNumber*feedbackValue+feedback)/(feedbackNumber+1);
			feedbackNumber++;
		}
		responseMessage = this.tenantDAO.updateFeedback(feedbackValue, feedbackNumber, tenantId);
		return responseMessage;
	}
	
	public ResponseMessage updateFeedbackLessor(double feedback, Integer lessorId) {
		ResponseMessage responseMessage = this.lessorDAO.getLessorFromId(lessorId);
		Lessor lessor = (Lessor) responseMessage.getResponseObject();
		Integer feedbackNumber = lessor.getFeedback_number();
		Double feedbackValue = lessor.getFeedback_lessor();
		if(feedbackNumber == null || feedbackNumber < 1) {
			feedbackNumber = 1;
			feedbackValue = feedback;
		} else {
			feedbackValue = (feedbackNumber*feedbackValue+feedback)/(feedbackNumber+1);
			feedbackNumber++;
		}
		responseMessage = this.lessorDAO.updateFeedback(feedbackValue, feedbackNumber, lessorId);
		return responseMessage;
	}

}
