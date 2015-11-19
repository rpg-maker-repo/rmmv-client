package com.trinary.rmmv.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public abstract class RMMVClient {
	protected Client client;
	protected RMMVClientConfig config;
	protected String authString;
	
	public RMMVClient(RMMVClientConfig config) {
		this.config = config;
		this.authString = String.format(config.getAuthStringTemplate(), config.getAuthToken());
		setupClient();
	}
	
	protected void setupClient() {
		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
		client = ClientBuilder.newClient(cc);
	}
}