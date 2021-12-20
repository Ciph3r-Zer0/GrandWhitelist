package ir.ciph3r.grandwhitelist.configuration.yml;

import ir.ciph3r.grandwhitelist.GrandWhitelist;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Messages {
	private static Configuration configuration;
	private static final File file = new File(GrandWhitelist.getInstance().getDataFolder(), "messages.yml");

	public static void create() throws IOException {
		if (!(GrandWhitelist.getInstance().getDataFolder().exists())) {
			GrandWhitelist.getInstance().getDataFolder().mkdir();
		}

		if (!file.exists()) {
			try (InputStream in = GrandWhitelist.getInstance().getResourceAsStream("messages.yml")) {
				Files.copy(in, file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
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
