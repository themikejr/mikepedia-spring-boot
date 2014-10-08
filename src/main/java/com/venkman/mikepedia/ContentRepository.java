package com.venkman.mikepedia;

import java.util.ArrayList;
import java.util.List;

import com.venkman.mikepedia.beans.Post;

public class ContentRepository {
	
	private List<String> unparsedContentList;
	private List<Post> parsedPostList = new ArrayList<Post>();
	
	public void build() {
		System.out.println("Number of posts in unparsed content list: " + unparsedContentList.size());
		for (String unparsedPost: unparsedContentList) {
			Post newPost = ContentParser.parse(unparsedPost);
			parsedPostList.add(newPost);
		}
	}

	public List<String> getUnparsedContentList() {
		return unparsedContentList;
	}

	public void setUnparsedContentList(List<String> unparsedContentList) {
		this.unparsedContentList = unparsedContentList;
	}

	public List<Post> getParsedPostList() {
		return parsedPostList;
	}

	public void setParsedPostList(List<Post> parsedPostList) {
		this.parsedPostList = parsedPostList;
	}

}
