package com.trinary.rmmv.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.trinary.rpgmaker.ro.AuthenticationRO;
import com.trinary.rpgmaker.ro.TokenRO;

public class RMMVClient {
	protected Client client;
	protected RMMVClientConfig config;
	protected RMMVBasicAuthentication authenticationFilter;
	
	public RMMVClient(RMMVClientConfig config) {
		this.config = config;
		// Auth Token takes precedence over username/password
		this.authenticationFilter = new RMMVBasicAuthentication(config);
		setupClient();
	}
	
	protected void setupClient() {
		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
		client = ClientBuilder.newClient(cc);
		client.register(authenticationFilter);
	}
	
	public TokenRO authenticate(String username, String password) throws Exception {
		AuthenticationRO authentication = new AuthenticationRO();
		authentication.setUsername(config.username);
		authentication.setPassword(config.getPassword());
		Response res = client
				.target(config.baseUrl)
				.path("/rmmv-api/v1/token/")
				.request()
				.post(Entity.entity(authentication, MediaType.APPLICATION_JSON));
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(TokenRO.class);
	}
}