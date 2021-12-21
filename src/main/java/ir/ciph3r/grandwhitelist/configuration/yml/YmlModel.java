package ir.ciph3r.grandwhitelist.configuration.yml;

import ir.ciph3r.grandwhitelist.GrandWhitelist;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public abstract class YmlModel {
	private Configuration configuration;
	private File file;
	private String fileName;

	public YmlModel(String fileName) {
		this.fileName = fileName;
		this.file = new File(GrandWhitelist.getInstance().getDataFolder(), fileName);
	}

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

	public void save() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Configuration getConfig() {
		return configuration;
	}

	public abstract void init();
}
