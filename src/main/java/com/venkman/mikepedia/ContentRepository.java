package com.venkman.mikepedia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.venkman.mikepedia.beans.Post;

@Component
public class ContentRepository {
	
	private List<String> unparsedContentList;
	private Map<String, Post> contentMap = new HashMap<String, Post>();
	
	public void build() {
		System.out.println("Number of posts in unparsed content list: " + unparsedContentList.size());
		for (String unparsedPost: unparsedContentList) {
			Post newPost = ContentParser.parse(unparsedPost);
			System.out.println("Adding content to repo: " + newPost.getTitle());
			System.out.println("permalink: " + newPost.getPermaLink());
			System.out.println(newPost.getDateCreated());
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
