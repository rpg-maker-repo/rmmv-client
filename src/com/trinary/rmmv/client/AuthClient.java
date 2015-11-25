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

public class AuthClient {
	protected Client client;
	protected RMMVClientConfig config;
	
	public AuthClient(RMMVClientConfig config) {
		this.config = config;
		setupClient();
	}
	
	protected void setupClient() {
		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
		client = ClientBuilder.newClient(cc);
	}
	
	public TokenRO authenticate(String username, String password) throws Exception {
		AuthenticationRO authentication = new AuthenticationRO();
		authentication.setUsername(config.getUsername());
		authentication.setPassword(config.getPassword());
		Response res = client
				.target(config.baseUrl)
				.path("/rmmv-api/v1/token/")
				.request()
				.post(Entity.entity(authentication, MediaType.APPLICATION_JSON));
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed with status " + res.getStatus() + ".");
		}
		
		return res.readEntity(TokenRO.class);
	}
}