package ir.ciph3r.grandwhitelist.configuration.yml;

import ir.ciph3r.grandwhitelist.GrandWhitelist;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Servers {
	private static Configuration configuration;
	private static final File file = new File(GrandWhitelist.getInstance().getDataFolder(), "storage.yml");

	public static void create() throws IOException {
		if (!(GrandWhitelist.getInstance().getDataFolder().exists())) {
			GrandWhitelist.getInstance().getDataFolder().mkdir();
	}

		if (!file.exists()) {
			try (InputStream in = GrandWhitelist.getInstance().getResourceAsStream("storage.yml")) {
				Files.copy(in, file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
	}

	public static void init() {
		Map<String, ServerInfo> proxyServers = GrandWhitelist.getInstance().getProxy().getServers();
		Collection<String> localServers = getConfig().getSection("servers").getKeys();

		for (String s : localServers) {
			proxyServers.remove(s);
		}
		for (String s : proxyServers.keySet()) {
			getConfig().set("servers." + s + ".whitelist", false);
			getConfig().set("servers." + s + ".whitelisted", new ArrayList<String>());
		}
		saveConfig();
	}

	public static Configuration getConfig() {
		return configuration;
	}

	public static void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
