package ir.ciph3r.grandwhitelist;

import ir.ciph3r.grandwhitelist.commands.GWCommands;
import ir.ciph3r.grandwhitelist.configuration.yml.Messages;
import ir.ciph3r.grandwhitelist.configuration.yml.Servers;
import ir.ciph3r.grandwhitelist.listeners.V_1_18;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public final class GrandWhitelist extends Plugin {

	//TODO: fix Command permissions
	//TODO: fix whitelist name case sensitivity

	private static GrandWhitelist plugin;

	@Override
	public void onEnable() {
		plugin = this;

		try {
			Servers.create();
			Messages.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Servers.init();
		getProxy().getPluginManager().registerCommand(this, new GWCommands("grandwhitelist"));
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
