package ir.ciph3r.grandwhitelist;

import ir.ciph3r.grandwhitelist.commands.GWCommands;
import ir.ciph3r.grandwhitelist.configuration.data.Data;
import ir.ciph3r.grandwhitelist.configuration.yml.Config;
import ir.ciph3r.grandwhitelist.configuration.yml.Messages;
import ir.ciph3r.grandwhitelist.listeners.V_1_18;
import net.md_5.bungee.api.plugin.Plugin;

public final class GrandWhitelist extends Plugin {

	//TODO: fix whitelist name case sensitivity

	private static GrandWhitelist plugin;

	@Override
	public void onEnable() {
		plugin = this;

		new Config().setup();
		new Messages().setup();
		new Data().initializeData();

		getProxy().getPluginManager().registerCommand(this, new GWCommands("GrandWhitelist"));
		getProxy().getPluginManager().registerListener(this, new V_1_18());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static GrandWhitelist getInstance() {
		return plugin;
	}
}
