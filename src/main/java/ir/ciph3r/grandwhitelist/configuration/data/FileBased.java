package ir.ciph3r.grandwhitelist.configuration.data;

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
import java.util.List;
import java.util.Map;

public class FileBased implements DataModel{
	private Configuration configuration;
	private String fileName;
	private File file;

	public FileBased(String fileName) {
		this.fileName = fileName;
		file = new File(GrandWhitelist.getInstance().getDataFolder(), fileName);
	}

	@Override
	public void setup() {
		if (!(file.getParentFile().exists())) file.getParentFile().mkdir();

		if (!(file.exists())) {
			try (InputStream in = GrandWhitelist.getInstance().getResourceAsStream(fileName)) {
				Files.copy(in, file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				//TODO: maybe a better way?
				try {
					configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		init();
	}

	@Override
	public void init() {
		Map<String, ServerInfo> proxyServers = GrandWhitelist.getInstance().getProxy().getServers();
		Collection<String> localServers = configuration.getSection("servers").getKeys();

		for (String s : localServers) {
			proxyServers.remove(s);
		}
		for (String s : proxyServers.keySet()) {
			configuration.set("servers." + s + ".whitelist", false);
			configuration.set("servers." + s + ".whitelisted", new ArrayList<String>());
		}
		saveConfig();
	}

	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<String> getServerList() {
		return configuration.getSection("servers").getKeys();
	}

	@Override
	public List<String> getWhitelistPlayerList(String serverName) {
		return configuration.getStringList("servers." + serverName + ".whitelisted");
	}

	@Override
	public boolean isWhitelisted(String serverName) {
		if (configuration.getBoolean("servers." + serverName + ".whitelist")) {
			return true;
		} else { return false; }
	}

	@Override
	public boolean serverExists(String serverName) {
		return getServerList().contains(serverName);
	}

	@Override
	public boolean playerExistsInWhitelist(String serverName, String playerName) {
		return getWhitelistPlayerList(serverName).contains(playerName);
	}

	@Override
	public void setWhitelistStatus(String serverName, boolean status) {
		configuration.set("servers." + serverName + ".whitelist", status);
		saveConfig();
	}

	@Override
	public void addPlayerToWhitelist(String serverName, String playerName) {
		List<String> players = getWhitelistPlayerList(serverName);
		players.add(playerName);
		configuration.set("servers." + serverName + ".whitelisted", players);
		saveConfig();
	}

	@Override
	public void removePlayerFromWhitelist(String serverName, String playerName) {
		List<String> players = getWhitelistPlayerList(serverName);
		players.remove(playerName);
		configuration.set("servers." + serverName + ".whitelisted", players);
		saveConfig();
	}
}
