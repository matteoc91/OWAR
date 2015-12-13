package com.sscsweb.owar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {

    @RequestMapping(value = "/")
    public ModelAndView getHomePage() {
        ModelAndView mv = new ModelAndView("homePage");
        return mv;
    }
	
}
