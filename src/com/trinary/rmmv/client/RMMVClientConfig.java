package com.trinary.rmmv.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RMMVClientConfig {
	protected String baseUrl;
	protected String authToken;
	protected String authStringTemplate;
	
	public RMMVClientConfig(String propertiesFile) {
		this(new File(propertiesFile));
	}
	
	public RMMVClientConfig(File file) {
		InputStream in;
		try {
			in = new FileInputStream(file);
			Properties props = new Properties();
			props.load(in);
			
			baseUrl = props.getProperty("client.baseUrl");
			authToken = props.getProperty("client.authToken");
			authStringTemplate = props.getProperty("client.authStringTemplate");
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public RMMVClientConfig() {}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * @return the authStringTemplate
	 */
	public String getAuthStringTemplate() {
		return authStringTemplate;
	}

	/**
	 * @param authStringTemplate the authStringTemplate to set
	 */
	public void setAuthStringTemplate(String authStringTemplate) {
		this.authStringTemplate = authStringTemplate;
	}
}