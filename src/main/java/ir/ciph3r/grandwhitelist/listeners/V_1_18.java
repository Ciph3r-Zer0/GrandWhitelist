package ir.ciph3r.grandwhitelist.listeners;

import ir.ciph3r.grandwhitelist.configuration.data.Data;
import ir.ciph3r.grandwhitelist.configuration.yml.Messages;
import ir.ciph3r.grandwhitelist.utilities.Utils;
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
				event.setCancelReason(Utils.colorizedComponent(Messages.WHITELIST_MESSAGE));
				event.setCancelled(true);
			}
		} else if (Data.dataModel.isWhitelisted("bungee")) {
			if (Data.dataModel.playerExistsInWhitelist("all", event.getConnection().getName())) return;
			if (!(Data.dataModel.playerExistsInWhitelist("bungee", event.getConnection().getName()))) {
				event.setCancelReason(Utils.colorizedComponent(Messages.WHITELIST_MESSAGE));
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
				Utils.sendMessage(event.getPlayer(), Messages.WHITELIST_MESSAGE);
				event.setCancelled(true);
			}
		} else if (Data.dataModel.isWhitelisted(event.getTarget().getName())) {
			if (Data.dataModel.playerExistsInWhitelist("all", event.getPlayer().getName())) return;
			if (!(Data.dataModel.playerExistsInWhitelist(event.getTarget().getName(), event.getPlayer().getName()))) {
				Utils.sendMessage(event.getPlayer(), Messages.WHITELIST_MESSAGE);
				event.setCancelled(true);
			}
		}
	}
}
