package ir.ciph3r.grandwhitelist.listeners;

import ir.ciph3r.grandwhitelist.configuration.data.Data;
import ir.ciph3r.grandwhitelist.configuration.yml.Messages;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class V_1_18 implements Listener {

	@EventHandler
	public void onConnect(LoginEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (Data.dataModel.isWhitelisted("all")) {
			if (!(Data.dataModel.playerExistsInWhitelist("all", event.getConnection().getName()))) {
				event.setCancelReason(Messages.WHITELIST_MESSAGE);
				event.setCancelled(true);
			}
		} else if (Data.dataModel.isWhitelisted("bungee")) {
			if (!(Data.dataModel.playerExistsInWhitelist("bungee", event.getConnection().getName()))) {
				event.setCancelReason(Messages.WHITELIST_MESSAGE);
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onSwitch(ServerConnectEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (Data.dataModel.isWhitelisted("all")) {
			if (!(Data.dataModel.playerExistsInWhitelist("all", event.getPlayer().getName()))) {
				event.getPlayer().sendMessage(TextComponent.fromLegacyText(Messages.WHITELIST_MESSAGE));
				event.setCancelled(true);
			}
		} else if (Data.dataModel.isWhitelisted(event.getTarget().getName())) {
			if (!(Data.dataModel.playerExistsInWhitelist(event.getTarget().getName(), event.getPlayer().getName()))) {
				event.getPlayer().sendMessage(TextComponent.fromLegacyText(Messages.WHITELIST_MESSAGE));
				event.setCancelled(true);
			}
		}
	}
}
