package com.restful.microservices.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.utils.URIBuilder;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import static spark.Spark.*;
import spark.*;

@Component
public class DropBoxAuthClient {

	@Autowired
	private Environment env;
	
	//DbxClient dbxClient;

	public DbxClient authDropbox(String dropBoxAppKey, String dropBoxAppSecret,String appName) throws IOException, DbxException {
		DbxClient dbxClient;
		DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropBoxAppSecret);
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig(appName,
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(dbxRequestConfig, dbxAppInfo);
		String authorizeUrl = dbxWebAuthNoRedirect.start();
		System.out.println("1. Authorize: Go to URL and click Allow : " + authorizeUrl);
		System.out.println("2. Auth Code: Copy authorization code and input here ");
		String dropboxAuthCode = env.getProperty("dropbox.access.token");
		DbxAuthFinish authFinish = dbxWebAuthNoRedirect.finish(dropboxAuthCode);
		String authAccessToken = authFinish.accessToken;
		dbxClient = new DbxClient(dbxRequestConfig, authAccessToken);
		System.out.println("Dropbox Account Name: " + dbxClient.getAccountInfo().displayName);
		return dbxClient;
	}
	
	/* returns Dropbox size in GB */
	public long getDropboxSize(DbxClient dbxClient) throws DbxException {
		long dropboxSize = 0;
		DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
		// in GB :)
		dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
		return dropboxSize;
	}

	public void uploadToDropbox(String fileName,DbxClient dbxClient) throws DbxException,
			IOException {
		File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile);
		try {
			DbxEntry.File uploadedFile = dbxClient.uploadFile("/" + fileName,
					DbxWriteMode.add(), inputFile.length(), fis);
			String sharedUrl = dbxClient.createShareableUrl("/" + fileName);
			System.out.println("Uploaded: " + uploadedFile.toString() + " URL "
					+ sharedUrl);
		} finally {
			fis.close();
		}
	}

	public void createFolder(String folderName,DbxClient dbxClient) throws DbxException {
		dbxClient.createFolder("/" + folderName);
	}

	public void listDropboxFolders(String folderPath,DbxClient dbxClient) throws DbxException {
		DbxEntry.WithChildren listing = dbxClient
				.getMetadataWithChildren(folderPath);
		System.out.println("Files List:");
		for (DbxEntry child : listing.children) {
			System.out.println("	" + child.name + ": " + child.toString());
		}
	}

	public void downloadFromDropbox(String fileName,DbxClient dbxClient) throws DbxException,
			IOException {
		FileOutputStream outputStream = new FileOutputStream(fileName);
		try {
			DbxEntry.File downloadedFile = dbxClient.getFile("/" + fileName,
					null, outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
		} finally {
			outputStream.close();
		}
	}
	
	public static String generateCSRFToken() {
		byte[] b = new byte[18];
		new SecureRandom().nextBytes(b);
		return Base64.encodeBase64URLSafeString(b);
	}

	public static String getRedirectURI(spark.Request request) throws URISyntaxException {
		return new URIBuilder(request.url()).setPath("/callback").build().toString();
	}
}
