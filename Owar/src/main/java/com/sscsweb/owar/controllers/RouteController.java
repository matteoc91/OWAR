package com.sscsweb.owar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sscsweb.owar.sessionBean.UserBean;

@Controller
public class RouteController {
	
	@Autowired
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
	
	/* -------- */

    @RequestMapping(value = "/")
    public ModelAndView getHomePage() {
    	ModelAndView mv = new ModelAndView("homePage");
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
	
}
