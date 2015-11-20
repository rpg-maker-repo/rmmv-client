package com.trinary.rmmv.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public abstract class RMMVClient {
	protected Client client;
	protected RMMVClientConfig config;
	protected RMMVBasicAuthentication authenticationFilter;
	protected String authString;
	
	public RMMVClient(RMMVClientConfig config) {
		this.config = config;
		// Auth Token takes precedence over username/password
		if (config.getAuthToken() != null) {
			this.authenticationFilter = new RMMVBasicAuthentication(config.getAuthToken());
		} else if (config.getUsername() != null && config.getPassword() != null) {
			this.authenticationFilter = new RMMVBasicAuthentication(config.getUsername(), config.getPassword());
		}
		setupClient();
	}
	
	protected void setupClient() {
		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
		client = ClientBuilder.newClient(cc);
		client.register(authenticationFilter);
	}
}