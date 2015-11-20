package com.trinary.rmmv.client;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trinary.rpgmaker.ro.PluginBaseRO;
import com.trinary.rpgmaker.ro.PluginRO;

public class PluginClient extends RMMVClient {
	
	public PluginClient(RMMVClientConfig config) {
		super(config);
	}
	
	public PluginBaseRO get(Long id) throws Exception {
		Response res = client
			.target(config.getBaseUrl())
			.path("/rmmv-api/v1/base/" + id)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
			
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(PluginBaseRO.class);
	}
	
	public List<PluginBaseRO> getAll() throws Exception {
		Response res = client
			.target(config.getBaseUrl())
			.path("/rmmv-api/v1/base/")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
			
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(new GenericType<List<PluginBaseRO>>(){});
	}
	
	public PluginBaseRO create(PluginBaseRO pluginBase) throws Exception {
		Response res = client
			.target(config.getBaseUrl())
			.path("/rmmv-api/v1/base/")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.entity(pluginBase, MediaType.APPLICATION_JSON));
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(PluginBaseRO.class);
	}
	
	public PluginBaseRO create(PluginBaseRO pluginBase, PluginRO initialVersion) throws Exception {		
		pluginBase = create(pluginBase);
		addVersion(pluginBase, initialVersion);
		
		return pluginBase;
	}
	
	public PluginBaseRO create(PluginBaseRO pluginBase, PluginRO initialVersion, List<PluginRO> initialVersionDependencies) throws Exception {		
		return null;
	}
	
	public PluginRO addVersion(PluginBaseRO pluginBase, PluginRO version) throws Exception {
		return addVersion(pluginBase.getId(), version);
	}
	
	public PluginRO addVersion(Long id, PluginRO version) throws Exception {
		Response res = client
			.target(config.getBaseUrl())
			.path("/rmmv-api/v1/base/" + id + "/version")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.entity(version, MediaType.APPLICATION_JSON));
		
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(PluginRO.class);
	}
	
	public List<PluginRO> getVersions(Long id) throws Exception {
		Response res = client
			.target(config.getBaseUrl())
			.path("/rmmv-api/v1/base/" + id + "/version")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
			
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(new GenericType<List<PluginRO>>(){});
	}
	
	public List<PluginRO> getLatestVersion(Long id) throws Exception {
		Response res = client
			.target(config.getBaseUrl())
			.path("/rmmv-api/v1/base/" + id + "/version")
			.queryParam("latest", true)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
			
		if (res.getStatus() < 200 || res.getStatus() >= 300) {
			throw new Exception("Call failed.");
		}
		
		return res.readEntity(new GenericType<List<PluginRO>>(){});
	}
	
	public List<PluginRO> getLatestVersion(PluginBaseRO plugin) throws Exception {
		return getLatestVersion(plugin.getId());
	}
	
	
}