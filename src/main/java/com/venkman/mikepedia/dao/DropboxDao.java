package com.venkman.mikepedia.dao;

import java.io.File;
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
	public static List<File> loadContentFromDropbox() throws DbxException {
		DbxEntry.WithChildren listing = client.getMetadataWithChildren("/Mikepedia");
	    System.out.println("Files in the root path:");
	    for (DbxEntry child : listing.children) {
	        System.out.println("	" + child.name + ": " + child.toString());
	    }
	    return null;
	}

}
