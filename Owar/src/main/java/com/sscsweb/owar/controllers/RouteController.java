package com.sscsweb.owar.controllers;

import javax.enterprise.context.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sscsweb.owar.sessionBean.UserBean;

@Controller
public class RouteController {
	
	@Autowired
	@SessionScoped
	private UserBean userBean;
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	/* SET SESSION DATA */
	
	@ModelAttribute("userBean")
	public String addUserToSession() {
		String user = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			user = mapper.writeValueAsString(userBean.getUser());
		} catch (JsonProcessingException e) {
			user = null;
		}
		return user;
	}
	
	@ModelAttribute("administratorBean")
	public String addAdminToSession() {
		String admin = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			admin = mapper.writeValueAsString(userBean.getAdministrator());
		} catch(JsonProcessingException e) {
			admin = null;
		}
		return admin;
	}
	
	@ModelAttribute("isAdminMode")
	public boolean isAdminMode() {
		return this.userBean.getAdministrator() != null && !this.userBean.isAdminMode();
	}
	
	@ModelAttribute("isEditMode")
	public boolean isEditMode() {
		return this.userBean.isEditMode();
	}
	
	@ModelAttribute("isTenantMode")
	public boolean isTenantMode() {
		return this.userBean.isTenantMode();
	}
	
	@ModelAttribute("isLessorMode")
	public boolean isLessorMode() {
		return this.userBean.isLessorMode();
	}
	
	@ModelAttribute("tenantBean")
	public String addTenantToSession() {
		String tenant = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			tenant = mapper.writeValueAsString(userBean.getTenant());
		} catch(JsonProcessingException e) {
			tenant = null;
		}
		return tenant;
	}
	
	@ModelAttribute("lessorBean")
	public String addLessorToSession() {
		String lessor = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			lessor = mapper.writeValueAsString(userBean.getLessor());
		} catch(JsonProcessingException e) {
			lessor = null;
		}
		return lessor;
	}
	
	@ModelAttribute("checkTenant")
	public boolean checkTenant() {
		return this.userBean.checkTenant();
	}
	
	/* -------- */

    @RequestMapping(value = "/")
    public ModelAndView getHomePage() {
    	ModelAndView mv = new ModelAndView("homePage");
        return mv;
    }
    
    @RequestMapping(value = "/validate")
    public ModelAndView getHomePageValidated() {
    	ModelAndView mv = new ModelAndView("homePage");
    	mv.addObject("isValidated", true);
        return mv;
    }
    
    @RequestMapping(value = "/registrationPage")
    public ModelAndView getRegistrationPage() {
    	ModelAndView mv = new ModelAndView("registrationPage");
    	return mv;
    }
    
    @RequestMapping(value = "/loginPage")
    public ModelAndView getLoginPage() {
    	ModelAndView mv = new ModelAndView("loginPage");
    	return mv;
    }
    
    @RequestMapping(value = "/socialPage")
    public ModelAndView getSocialPage() {
    	ModelAndView mv = new ModelAndView("socialPage");
    	return mv;
    }
    
    @RequestMapping(value = "/profilePage")
    public ModelAndView getProfilePage() {
    	ModelAndView mv = new ModelAndView("profilePage");
    	return mv;
    }
    
    @RequestMapping(value = "/officePage")
    public ModelAndView getOfficePage(@RequestParam(value = "status", required = false, defaultValue = "") String status) {
    	ModelAndView mv = new ModelAndView("officePage");
    	if(status.equalsIgnoreCase("OK")) {
    		mv.addObject("status", "OK");
    	}
    	return mv;
    }
    
    @RequestMapping(value = "/servicePage")
    public ModelAndView getServicePage() {
    	ModelAndView mv = new ModelAndView("servicePage");
    	return mv;
    }
    
    @RequestMapping(value = "/officeDetailPage/{id}")
    public ModelAndView getOfficeDetailPage(@PathVariable Integer id) {
    	ModelAndView mv = new ModelAndView("officeDetailPage");
    	mv.addObject("officeId", id);
    	return mv;
    }
	
}
