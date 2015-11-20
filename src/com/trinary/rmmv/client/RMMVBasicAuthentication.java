package com.trinary.rmmv.client;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class RMMVBasicAuthentication implements ClientRequestFilter {
	protected String authString;
	
	public RMMVBasicAuthentication(String username, String password) {
		this.authString = java.util.Base64.getEncoder().encodeToString(new String(username + ":" + password).getBytes());
	}
	
	public RMMVBasicAuthentication(String authString) {
		this.authString = authString;
	}
	
	@Override
	public void filter(ClientRequestContext ctx) throws IOException {
		ctx.getHeaders().add("Authorization", "Basic " + authString);
	}
}