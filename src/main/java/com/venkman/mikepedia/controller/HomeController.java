package com.venkman.mikepedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.venkman.mikepedia.Application;

@Controller
public class HomeController {
	
	private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String greeting(Model model) {
    	log.info("getHome");
        return "home";
    }

}
