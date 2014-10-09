package com.venkman.mikepedia.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.venkman.mikepedia.beans.Post;
import com.venkman.mikepedia.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	BlogService blogService;

    @RequestMapping("/blog")
    public String getBlog(Model model, HttpServletResponse response) throws IOException {
    	try {
    		List<Post> sortedEntries = blogService.contentOrderedByDescendingDate();
			System.out.println("Adding blog entries to model, size: " + sortedEntries.size());
    		model.addAttribute("blogEntries", sortedEntries);
		} catch (ParseException e) {
			e.printStackTrace();
			response.sendError(500);
		}
        return "blog";
    }
    
    @RequestMapping("/blog/{articlePermaLink}")
    public String getBlogPost(@PathVariable("articlePermaLink") String articlePermaLink, Model model, HttpServletResponse response) throws IOException {
    	Post theRequestedPost = blogService.findArticle(articlePermaLink);
    	if (theRequestedPost != null) {
    		System.out.println("Found post object, adding to model: " + theRequestedPost.getTitle());
    		model.addAttribute("article", theRequestedPost);
    	} else {
    		response.sendError(401);
    	}
    	return "article";
    }

}
