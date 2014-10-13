package com.venkman.mikepedia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venkman.mikepedia.beans.Post;

public class PostComparator implements Comparator<Post>{
	
	private final static Logger log  = LoggerFactory.getLogger(PostComparator.class);

	@Override
	public int compare(Post post1, Post post2) {
		Date post1Date = null;
		Date post2Date = null;
		int returnValue = 0;
		try {
			post1Date = convertStringToDate(post1.getDateCreated());
			post2Date = convertStringToDate(post2.getDateCreated());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (post1Date != null && post2Date != null) {
			returnValue = post2Date.compareTo(post1Date);
		}
		return returnValue;
	}
	
	private Date convertStringToDate(String stringDate) throws ParseException {
		log.info("convertStringToDate()");
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yy");
		return formatter.parse(stringDate);
	}

}
