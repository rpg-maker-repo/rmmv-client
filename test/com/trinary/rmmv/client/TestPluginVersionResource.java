package com.trinary.rmmv.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.trinary.rpgmaker.ro.PluginRO;

public class TestPluginVersionResource {

	@Test
	public void testGetAllVersions() {
		PluginVersionClient client = new PluginVersionClient();
		List<PluginRO> plugins = null;
		try {
			plugins = client.getAllPlugins();
		} catch (Exception e) {
			fail(e.getMessage());
			return;
		}
		
		assertNotNull(plugins);
		return;
	}
}