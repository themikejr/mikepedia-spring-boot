package com.venkman.mikepedia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.venkman.mikepedia.beans.Post;
import com.venkman.mikepedia.service.BlogService;

@Controller
public class HomeController {
	
	private final static Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired BlogService blogService;

    @RequestMapping("/")
    public String greeting(Model model) {
    	log.info("getHome");
    	List<Post> recentArticles = blogService.getThreeMostRecentArticles();
    	model.addAttribute("article1", recentArticles.get(0));
    	model.addAttribute("article2", recentArticles.get(1));
    	model.addAttribute("article3", recentArticles.get(2));
        return "home";
    }

}
