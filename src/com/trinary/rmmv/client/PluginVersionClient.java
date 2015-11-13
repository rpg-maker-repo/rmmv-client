package com.trinary.rmmv.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.trinary.rpgmaker.ro.PluginBaseRO;
import com.trinary.rpgmaker.ro.PluginRO;

public class PluginVersionClient {
	protected Client client;
	protected String baseUrl = "http://localhost:8080";
	
	public PluginVersionClient() {
		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
		client = ClientBuilder.newClient(cc);
	}
	
	public PluginRO addDependencies(PluginRO plugin, List<PluginRO> dependencies) throws Exception {
		return addDependencies(plugin.getId(), dependencies);
	}
	
	public PluginRO addDependencies(long id, List<PluginRO> dependencies) throws Exception {
		Response res = client
			.target(baseUrl)
			.path("/rmmv-api/v1/plugin/" + id + "/dependency")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.entity(dependencies, MediaType.APPLICATION_JSON));
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(PluginRO.class);
	}
	
	public List<PluginRO> getDependencies(PluginRO plugin) throws Exception {
		return getDependencies(plugin.getId());
	}
	
	public List<PluginRO> getDependencies(Long id) throws Exception {
		Response res = client
			.target(baseUrl)
			.path("/rmmv-api/v1/plugin/" + id + "/dependency")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(new GenericType<List<PluginRO>>(){});
	}
	
	public PluginRO getPlugin(Long id) throws Exception {
		Response res = client
			.target(baseUrl)
			.path("/rmmv-api/v1/plugin/" + id)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(PluginRO.class);
	}
	
	public List<PluginRO> getAllPlugins() throws Exception {
		Response res = client
			.target(baseUrl)
			.path("/rmmv-api/v1/plugin")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}

		return res.readEntity(new GenericType<List<PluginRO>>(){});
	}
	
	public String getScript(PluginRO plugin) throws Exception {
		return getScript(plugin.getId());
	}
	
	public String getScript(Long id) throws Exception {
		Response res = client
			.target(baseUrl)
			.path("/rmmv-api/v1/plugin/" + id + "/script")
			.request()
			.accept(MediaType.TEXT_PLAIN)
			.get();
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}

		return res.readEntity(String.class);
	}
	
	public PluginBaseRO getBase(PluginRO plugin) {
		return getBase(plugin);
	}
	
	public PluginBaseRO getBase(Long id) throws Exception {
		Response res = client
				.target(baseUrl)
				.path("/rmmv-api/v1/plugin/" + id + "/base")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
			
			if (res.getStatus() < 200 || res.getStatus() >= 300) {
				throw new Exception("Call failed.");
			}

			return res.readEntity(PluginBaseRO.class);
	}
	
	public List<PluginRO> getPluginByHash(String hash) throws Exception {
		Response res = client
				.target(baseUrl)
				.path("/rmmv-api/v1/plugin")
				.queryParam("hash", hash)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
			
			if (res.getStatus() < 200 || res.getStatus() >= 300) {
				throw new Exception("Call failed.");
			}

			return res.readEntity(new GenericType<List<PluginRO>>(){});
	}
}