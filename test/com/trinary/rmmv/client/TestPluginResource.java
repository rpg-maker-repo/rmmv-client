package com.trinary.rmmv.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.trinary.rpgmaker.ro.PluginBaseRO;
import com.trinary.rpgmaker.ro.PluginRO;

public class TestPluginResource {
	public static RMMVClientConfig config = new RMMVClientConfig("test.properties");
	
	public static PluginBaseRO createTestPlugin() {
		PluginBaseRO base = new PluginBaseRO();
		base.setName("Super Duper Plugin");
		base.setDescription("HOLY SHIT!  THIS IS AWESOME!");
		
		return base;
	}
	
	public static PluginRO createTestVersion() {
		PluginRO version = new PluginRO();
		version.setVersion("1.00 RELEASE");
		version.setCompatibleRMVersion("RMMV1.0+");
		version.setScript("console.log('BURT!')");
		
		return version;
	}
	
	@Test
	public void testGetAllPlugins() {
		PluginClient client = new PluginClient(config);
		List<PluginBaseRO> plugins = null;
		try {
			plugins = client.getAll();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		assertNotNull(plugins);
	}
	
	@Test
	public void testCreateNew1() {
		PluginClient client = new PluginClient(config);
		
		PluginBaseRO base = createTestPlugin();
		
		try {
			base = client.create(base);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		if (base.getId() == 0) {
			fail("ID not generated");
		}
	}
	
	@Test
	public void testCreateNew2() {
		PluginClient client = new PluginClient(config);
		
		PluginBaseRO base = createTestPlugin();
		PluginRO version = createTestVersion();
		
		try {
			base = client.create(base, version);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		if (base.getId() == 0) {
			fail("ID not generated");
		}
	}
	
	@Test
	public void testCreateVersion() {
		PluginClient client = new PluginClient(config);
		
		PluginBaseRO base = createTestPlugin();
		PluginRO version = createTestVersion();
		
		try {
			base = client.create(base, version);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		if (base.getId() == 0) {
			fail("ID not generated");
		}
		
		try {
			version = client.addVersion(base.getId(), version);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		if (version.getId() == 0) {
			fail("ID not generated");
		}
	}
}