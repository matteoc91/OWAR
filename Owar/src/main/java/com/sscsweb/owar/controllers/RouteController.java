package com.sscsweb.owar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/")
    public ModelAndView getHomePage() {
    	ObjectMapper mapper = new ObjectMapper();
        ModelAndView mv = new ModelAndView("homePage");
        try {
			mv.addObject("userBean", mapper.writeValueAsString(userBean.getUser()));
		} catch (JsonProcessingException e) {
			mv.addObject("userBean", null);
		}
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
	
}
