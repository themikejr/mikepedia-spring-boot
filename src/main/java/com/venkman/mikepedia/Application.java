package com.venkman.mikepedia;

import java.io.IOException;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.venkman.mikepedia.dao.DropboxDao;

@ComponentScan
@EnableAutoConfiguration
public class Application {
	
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
    try {
    	DropboxDao.initializeDropboxConnection(authCode);
    	System.out.println("Dropbox connection initialized.");
    	DropboxDao.loadContentFromDropbox();
    	System.out.println("Content loaded from Dropbox");
    } catch (Exception e) {
    	System.out.println("Something when wrong when loading dropbox content.");
    	e.printStackTrace();
    	System.out.println("Exiting...");
    	System.exit(1);
    }
        
        
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
