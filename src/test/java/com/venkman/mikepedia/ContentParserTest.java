package com.venkman.mikepedia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.venkman.mikepedia.beans.Post;

public class ContentParserTest {
	
	private ContentParser contentParser;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testParserCanExtractYamlDataProperly() {
		String unparsedSamplePost = getSampleUnparsedPost();
		Map<String, String> result = contentParser.extractYamlFrontMatter(unparsedSamplePost);
		
		// Assertion
		assertEquals("Welcome to my blog", result.get("title"));
	}
	
	@Test
	public void testPostHasYamlWhenItShouldBeTrue() {
		String post = getSampleUnparsedPost();
		boolean result = contentParser.postHasYaml(post);
		assertTrue(result);
	}
	
	@Test
	public void testPostCanBeBuiltProperly() {
		
		// Execution
		Post result = contentParser.parse(getSampleUnparsedPost());
		
		// Assertion
		assertEquals("Welcome to my blog", result.getTitle());
		assertEquals(false, result.isPublished());
		assertEquals("10.8.14", result.getDateCreated());
		assertEquals("10.8.14", result.getDateLastModified());
		assertEquals(false, result.isPublished());
		assertNotNull(result.getTags());
		assertEquals(2, result.getTags().size());
		assertEquals("web-dev", result.getTags().get(0));
		assertEquals("blog-announcements", result.getTags().get(1));
		assertNotNull(result.getContent());
		assertTrue(result.getContent().startsWith("<h1>Welcome to my blog.</h1>"));
		
	}
	
	@Test
	public void testPostCanBeConvertedFromMarkdownToHtmlProperly() {
		String result = contentParser.convertMarkdownToHtml(getSampleUnparsedPost());
		System.out.println(result);
		assertTrue(result.startsWith("<h1>Welcome to my blog.</h1>"));
	}
	
	private String getSampleUnparsedPost() {
		return "---\n" + 
				"title: Welcome to my blog\n" + 
				"published: false\n" + 
				"dateCreated: 10.8.14\n" + 
				"dateLastModified: 10.8.14\n" + 
				"tags: web-dev blog-announcements\n" + 
				"---\n" + 
				"\n" + 
				"# Welcome to my blog.\n" + 
				"\n" + 
				"## Stack\n" + 
				"\n" + 
				"This blog is powered by a Spring Boot app (java). It uses dropbox as it's content repository.\n" + 
				"It reads markdown files with YAML front matter from a directory in my dropbox account.";
	}

}
