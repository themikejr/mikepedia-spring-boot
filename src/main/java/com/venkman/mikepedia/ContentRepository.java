package com.venkman.mikepedia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.venkman.mikepedia.beans.Post;

@Component
public class ContentRepository {
	
	private final static Logger log = LoggerFactory.getLogger(ContentRepository.class);
	private List<String> unparsedContentList;
	private Map<String, Post> contentMap = new HashMap<String, Post>();
	
	public void build() {
		System.out.println("Number of posts in unparsed content list: " + unparsedContentList.size());
		for (String unparsedPost: unparsedContentList) {
			Post newPost = ContentParser.parse(unparsedPost);
			log.info("Adding content to repo: " + newPost.getTitle());
			log.info("permalink: " + newPost.getPermaLink());
			log.info(newPost.getDateCreated());
			contentMap.put(newPost.getPermaLink(), newPost);
		}
	}

	public List<String> getUnparsedContentList() {
		return unparsedContentList;
	}

	public void setUnparsedContentList(List<String> unparsedContentList) {
		this.unparsedContentList = unparsedContentList;
	}

	public Map<String, Post> getContentMap() {
		return contentMap;
	}

	public void setContentMap(Map<String, Post> contentMap) {
		this.contentMap = contentMap;
	}

}
