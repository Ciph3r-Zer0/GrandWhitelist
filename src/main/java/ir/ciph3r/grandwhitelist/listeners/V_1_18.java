package ir.ciph3r.grandwhitelist.listeners;

import ir.ciph3r.grandwhitelist.configuration.yml.Messages;
import ir.ciph3r.grandwhitelist.configuration.yml.Servers;
import ir.ciph3r.grandwhitelist.utilities.Utils;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public class V_1_18 implements Listener {

	@EventHandler
	public void onConnect(LoginEvent event) {
		if (event.isCancelled()) return;

		if (Servers.getConfig().getBoolean("servers.global.whitelist")) {
			List<String> whitelistedPlayers = Servers.getConfig().getStringList("servers.global.whitelisted");

			if (!(whitelistedPlayers.contains(event.getConnection().getName()))) {
				event.setCancelReason(new TextComponent(Utils.colorize(Messages.getConfig().getString("whitelisted_message"))));
				System.out.println("canceled by LoginEvent");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onSwitch(ServerConnectEvent event) {
		if (event.isCancelled()) return;
		ProxiedPlayer player = event.getPlayer();

		if (Servers.getConfig().getBoolean("servers." + event.getTarget().getName() + ".whitelist")) {
			List<String> whitelistedPlayers = Servers.getConfig().getStringList("servers." + event.getTarget().getName() + ".whitelisted");

			if (!(whitelistedPlayers.contains(player.getName()))) {
				event.setCancelled(true);
				System.out.println("canceled by ConnectEvent");
				player.sendMessage(new TextComponent(Utils.colorize(Messages.getConfig().getString("whitelisted_message"))));
			}
		}
	}
}
