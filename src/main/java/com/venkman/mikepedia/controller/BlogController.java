package com.venkman.mikepedia.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.venkman.mikepedia.Application;
import com.venkman.mikepedia.beans.Post;
import com.venkman.mikepedia.service.BlogService;

@Controller
public class BlogController {
	
	private final static Logger log = LoggerFactory.getLogger(BlogController.class);
	
	@Autowired BlogService blogService;

    @Cacheable("articles")
	@RequestMapping("/blog")
    public String getBlog(Model model, HttpServletResponse response) throws IOException {
    	log.info("getBlog()");
    	try {
    		List<Post> sortedEntries = blogService.contentOrderedByDescendingDate();
    		model.addAttribute("blogEntries", sortedEntries);
		} catch (ParseException e) {
			e.printStackTrace();
			response.sendError(500);
		}
        return "blog";
    }
    
    @RequestMapping("/blog/{articlePermaLink}")
    public String getBlogPost(@PathVariable("articlePermaLink") String articlePermaLink, Model model, HttpServletResponse response) throws IOException {
    	log.info("getArticle: " + articlePermaLink);
    	Post theRequestedPost = blogService.findArticle(articlePermaLink);
    	if (theRequestedPost != null) {
    		model.addAttribute("article", theRequestedPost);
    	} else {
    		response.sendError(401);
    	}
    	return "article";
    }

}
