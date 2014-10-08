package com.venkman.mikepedia.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;

public class DropboxDao {
	
	private static DbxClient client;
	
	public static void initializeDropboxConnection(String authCode) {
		DbxRequestConfig config = new DbxRequestConfig("Mikepedia", Locale.getDefault().toString());
		DropboxDao.client = new DbxClient(config, authCode);
	}
	
	// THIS DOESN'T WORK YET
	public static List<String> loadContentFromDropbox() throws DbxException, IOException {
		List<String> contentList = new ArrayList<String>();
		DbxEntry.WithChildren listing = client.getMetadataWithChildren("/Mikepedia");
	    for (DbxEntry potentialFile : listing.children) {
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	try {
	    		client.getFile(potentialFile.path, null, baos);
	    		contentList.add(baos.toString());
	    	} catch (Exception e) {
	    		
	    	} finally {
	    		baos.close();
	    	}
	    }
	    return contentList;
	}

}
