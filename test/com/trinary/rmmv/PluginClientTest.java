package com.trinary.rmmv;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.trinary.rmmv.client.PluginClient;
import com.trinary.rpgmaker.ro.PluginRO;

public class PluginClientTest {
	public PluginRO createTestPlugin() {
		PluginRO plugin = new PluginRO();
		plugin.setName("Test Plugin 1");
		plugin.setDescription("This plugin is fly.");
		plugin.setVersion("1.0a");
		plugin.setCompatibleRMVersion("RMMV1.0+");
		plugin.setScript("console.log()");
		
		return plugin;
	}
	
	public List<PluginRO> createTestDependencies() {
		List<PluginRO> plugins = new ArrayList<PluginRO>();
		
		PluginRO plugin1 = new PluginRO();
		plugin1.setName("Test Plugin 2");
		plugin1.setDescription("This plugin is sweet.");
		plugin1.setVersion("1.0b");
		plugin1.setCompatibleRMVersion("RMMV1.0+");
		plugin1.setScript("console.log()");
		
		PluginRO plugin2 = new PluginRO();
		plugin2.setName("Test Plugin 3");
		plugin2.setDescription("This plugin is fart.");
		plugin2.setVersion("0.1 RELEASE");
		plugin2.setCompatibleRMVersion("RMMV1.0+");
		plugin2.setScript("console.log()");
		
		plugins.add(plugin1);
		plugins.add(plugin2);
		
		return plugins;
	}
	
	@Test
	public void testCreatePlugin() {
		PluginClient client = new PluginClient();
		PluginRO plugin = createTestPlugin();
		try {
			plugin = client.createPlugin(plugin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(plugin);
	}
	
	@Test
	public void testAddDependencies(PluginRO plugin) {
		PluginClient client = new PluginClient();
		List<PluginRO> dependencies = createTestDependencies();
		
		List<PluginRO> savedDependencies = new ArrayList<PluginRO>();
		for (PluginRO dependency : dependencies) {
			PluginRO savedDependency = null;
			try {
				savedDependency = client.createPlugin(dependency);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			savedDependencies.add(savedDependency);
		}
		PluginRO savedPlugin = null;
		try {
			savedPlugin = client.addDependencies(plugin, savedDependencies);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(savedPlugin);
	}
	
	@Test
	public void testGetAllPlugins() {
		PluginClient client = new PluginClient();
		List<PluginRO> plugins = null;
		try {
			plugins = client.getAllPlugins();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(plugins);
		assertNotEquals(plugins.size(), 0);
	}
	
	@Test
	public void testGetPlugin() {
		PluginClient client = new PluginClient();
		PluginRO plugin = null;
		try {
			plugin = client.getPlugin((long)1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(plugin);
	}
	
	@Test
	public void testGetScript() {
		PluginClient client = new PluginClient();
		String script = null;
		try {
			script = client.getScript((long)1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(script);
	}
}