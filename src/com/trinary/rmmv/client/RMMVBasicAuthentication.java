package com.trinary.rmmv.client;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import com.trinary.rpgmaker.ro.TokenRO;

public class RMMVBasicAuthentication implements ClientRequestFilter {
	protected TokenRO token;
	protected AuthClient client;
	protected RMMVClientConfig config;
	
	public RMMVBasicAuthentication(RMMVClientConfig config) {
		this.config = config;
		this.client = new AuthClient(config);
	}
	
	@Override
	public void filter(ClientRequestContext ctx) throws IOException {
		if (token == null || token.isExpired()) {
			try {
				this.token = client.authenticate(config.getUsername(), config.getPassword());
			} catch (Exception e) {
				return;
			}
			
			if (token == null) {
				return;
			}
		}
		
		ctx.getHeaders().add("Authorization", "Bearer " + token.getToken());
	}
}