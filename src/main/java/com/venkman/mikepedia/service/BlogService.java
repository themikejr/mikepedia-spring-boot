package com.venkman.mikepedia.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venkman.mikepedia.Application;
import com.venkman.mikepedia.ContentRepository;
import com.venkman.mikepedia.beans.Post;

@Service
public class BlogService {
	
	public List<Post> contentOrderedByDescendingDate() throws ParseException {
		List<Post> contentList = retrieveContentMapAsList();
//		List<Post> sortedContentList = new ArrayList<Post>();
//		sortedContentList.add(contentList.get(0));
//		contentList.remove(0);
//		for(Post post: contentList) {
//			if (convertStringToDate(post.getDateCreated()).after(convertStringToDate(sortedContentList.get(0).getDateCreated()))) {
//				sortedContentList.add(1, post);
//			} else {
//				sortedContentList.add(0, post);
//			}
//		}
		return contentList;
	}
	
	public Post findArticle(String articlePermaLink) {
		Post returnValue = null;
		for (Entry contentEntry: Application.getContentRepository().getContentMap().entrySet()) {
			System.out.println("Checking to see if " + articlePermaLink + " matches " + contentEntry.getKey());
			if (contentEntry.getKey().equals(articlePermaLink)) {
				returnValue = (Post) contentEntry.getValue();
			}
		}
		return returnValue;
	}
	
	private List<Post> retrieveContentMapAsList() {
		List<Post> contentMapAsList = new ArrayList<Post>();
		System.out.println("Getting content from repo: " + Application.getContentRepository().getContentMap().size());
		for (Entry contentEntry: Application.getContentRepository().getContentMap().entrySet()) {
			System.out.println("Converting post from map to list: " + contentEntry.getKey());
			contentMapAsList.add((Post)contentEntry.getValue());
		}
		return contentMapAsList;
	}
	
	private Date convertStringToDate(String stringDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yy");
		return formatter.parse(stringDate);
	}

}
