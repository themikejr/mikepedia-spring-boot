package com.venkman.mikepedia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.pegdown.PegDownProcessor;

import com.venkman.mikepedia.beans.Post;

public class ContentParser {
	
	
	public static Post parse(String unparsedPost) {
		Post returnValue = new Post();
		Map<String, String> yamlMap = extractYamlFrontMatter(unparsedPost);
		setMetaDataAccordingly(returnValue, yamlMap);
		String html = convertMarkdownToHtml(unparsedPost);
		returnValue.setContent(html);
		return returnValue;
	}
	
	protected static void setMetaDataAccordingly(Post thePost, Map<String, String> yamlMap) {
		thePost.setTitle(yamlMap.get("title"));
		thePost.setDateCreated(yamlMap.get("dateCreated"));
		thePost.setDateLastModified(yamlMap.get("dateLastModified"));
		thePost.setPublished(Boolean.parseBoolean(yamlMap.get("published")));
		thePost.setTags(Arrays.asList(yamlMap.get("tags").split(" ")));
		thePost.setPermaLink(yamlMap.get("permalink"));
		
	}
	
	protected static Map<String, String> extractYamlFrontMatter(String unparsedPost) {
		Map<String, String> returnValue = new HashMap<String, String>();
		if (postHasYaml(unparsedPost)) {
			String yamlFrontMatter = StringUtils.substringBetween(unparsedPost, "---");
			Scanner scanner = new Scanner(yamlFrontMatter);
			while (scanner.hasNextLine()) {
				  String line = scanner.nextLine();
				  String key = StringUtils.substringBefore(line, ":").trim();
				  String value = StringUtils.substringAfter(line, ":").trim();
				  returnValue.put(key, value);
				  
				}
			scanner.close();
		}
		return returnValue;
	}
	
	protected static String convertMarkdownToHtml(String unparsedPost) {
		String unparsedMarkdown = StringUtils.substringAfterLast(unparsedPost, "---");
		PegDownProcessor pegdown = new PegDownProcessor();
		String html = pegdown.markdownToHtml(unparsedMarkdown);
		return html;
		
	}
	
	protected static boolean postHasYaml(String post) {
		boolean returnValue = false;
		if (post.startsWith("---\n")) {
			returnValue = true;
		}
		returnValue = true;
		return returnValue;
	}
	

}
