package com.venkman.mikepedia;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.dropbox.core.DbxException;
import com.venkman.mikepedia.dao.DropboxDao;

@ComponentScan
@EnableAutoConfiguration
@EnableCaching
public class Application {
	
	private final static Logger log = LoggerFactory.getLogger(Application.class);
	private static ContentRepository contentRepository = new ContentRepository();
	
	public static void main(String[] args) throws IOException, DbxException {
		if (args.length != 1) {
			System.out.println("Only expected one CLI argument for dropbox authentication code.");
			System.exit(1);
		} else {
			
			initializeDropbox(args[0]);
	        SpringApplication.run(Application.class, args);
		}
    }
	
	private static void initializeDropbox(String authCode) throws IOException, DbxException {
        //final String APP_KEY = "gzsb2ch7rslegn2";
        //final String APP_SECRET = "r5bngf3f02ovf6u";

        //DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

      //  DbxRequestConfig config = new DbxRequestConfig("Mikepedia", Locale.getDefault().toString());
        //DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
        //String code = authorizeDropbox(webAuth);
     //   String code = "";
        //DbxAuthFinish authFinish = webAuth.finish(code);
        //String accessToken = authFinish.accessToken;
        
        // test api access
//        DbxClient client = new DbxClient(config, code);
//        try {
//        	System.out.println("Linked account: " + client.getAccountInfo().toString());
//        } catch (Exception e) {
//        	System.out.println("something went wrong!");
//        	e.printStackTrace();
//        }
		List<String> contentList = null;
		try {
			DropboxDao.initializeDropboxConnection(authCode);
			log.info("Dropbox connection initialized.");
			contentList = DropboxDao.loadContentFromDropbox();
			log.info("Content loaded from Dropbox");
		} catch (Exception e) {
			log.info("Something when wrong when loading dropbox content.");
			e.printStackTrace();
			log.info("Exiting...");
			System.exit(1);
		}
		
		if (contentList != null) {
			log.info("Adding content from dropbox to content repo.");
			getContentRepository().setUnparsedContentList(contentList);
			getContentRepository().build();
		}
        
        
	}
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("articles");
    }

	public static ContentRepository getContentRepository() {
		return contentRepository;
	}

	public void setContentRepository(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}
	
//	private static String authorizeDropbox(DbxWebAuthNoRedirect webAuth) throws IOException {
//		String authorizeUrl = webAuth.start();
//		System.out.println("1. Go to: " + authorizeUrl);
//		System.out.println("2. Click \"Allow\" (you might have to log in first)");
//		System.out.println("3. Copy the authorization code.");
//		String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
//		return code;
//	}
	

}
