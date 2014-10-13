package com.venkman.mikepedia.beans;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Post {
	
	String title;
	List<String> tags;
	String dateCreated;
	String dateLastModified;
	String content;
	String permaLink;
	String tagline;
	String subject;
	boolean isPublished;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateLastModified() {
		return dateLastModified;
	}
	public void setDateLastModified(String dateLastModified) {
		this.dateLastModified = dateLastModified;
	}
	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPermaLink() {
		return permaLink;
	}
	public void setPermaLink(String permaLink) {
		this.permaLink = permaLink;
	}
	
	public String getTagline() {
		return (StringUtils.isEmpty(tagline)) ? "Lorem ipsum." : tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

}
