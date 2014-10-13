package com.venkman.mikepedia.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.venkman.mikepedia.Application;
import com.venkman.mikepedia.beans.Post;
import com.venkman.mikepedia.util.PostComparator;

@Service
public class BlogService {
	
	private final static Logger log = LoggerFactory.getLogger(BlogService.class);
	
	public List<Post> contentOrderedByDescendingDate() throws ParseException {
		log.info("contentOrderedByDescendingDate()");
		List<Post> contentList = retrieveContentMapAsList();
		Collections.sort(contentList, new PostComparator());
		return contentList;
	}
	
	public List<Post> contentOrderedByDescendingDateForSubject(String subject) throws ParseException {
		log.info("contentOrderedByDescendingDateForSubjetc(): " + subject);
		List<Post> contentList = retrieveContentMapAsList();
		List<Post> filteredContentList = filterContentListForSubject(contentList, subject);
		Collections.sort(filteredContentList, new PostComparator());
		log.info("Returning filtered list size: " + filteredContentList.size());
		log.info(filteredContentList.toString());
		return filteredContentList;
	}
	
	public Post findArticle(String articlePermaLink) {
		log.info("findArticles: " + articlePermaLink);
		Post returnValue = null;
		for (Entry contentEntry: Application.getContentRepository().getContentMap().entrySet()) {
			if (contentEntry.getKey().equals(articlePermaLink)) {
				returnValue = (Post) contentEntry.getValue();
			}
		}
		return returnValue;
	}
	
	private List<Post> retrieveContentMapAsList() {
		log.info("retrieveContentMapAsList()");
		List<Post> contentMapAsList = new ArrayList<Post>();
		System.out.println("Getting content from repo: " + Application.getContentRepository().getContentMap().size());
		for (Entry contentEntry: Application.getContentRepository().getContentMap().entrySet()) {
			System.out.println("Converting post from map to list: " + contentEntry.getKey());
			contentMapAsList.add((Post)contentEntry.getValue());
		}
		return contentMapAsList;
	}
	
	private List<Post> filterContentListForSubject(List<Post> unfilteredContentList, String subjectToFilterFor) {
		List<Post> filteredContent = new ArrayList<Post>();
		for (Post post: unfilteredContentList) {
			if (StringUtils.equalsIgnoreCase(post.getSubject(), subjectToFilterFor)) {
				filteredContent.add(post);
			}
		}
		return filteredContent;
	}
	
	// Need to make this smarter
	public List<Post> getThreeMostRecentArticles() {
		Map<String, Post> contentMap = Application.getContentRepository().getContentMap();
		List<Post> contentList = new ArrayList<Post>();
		for (Entry contentEntry: contentMap.entrySet()) {
			contentList.add((Post)contentEntry.getValue());
		}
		return contentList;
	}

}
