package ir.ciph3r.grandwhitelist.commands;

import ir.ciph3r.grandwhitelist.configuration.data.Data;
import ir.ciph3r.grandwhitelist.configuration.yml.Messages;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class GWCommands extends Command {
	public GWCommands(String name) {
		super("GrandWhitelist", null, "GW", "Whitelist");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (args.length < 1) {
			Messages.USAGE_MESSAGE.forEach(s -> sender.sendMessage(TextComponent.fromLegacyText(s)));
		} else if (args.length < 2) {
			Messages.USAGE_MESSAGE.forEach(s -> sender.sendMessage(TextComponent.fromLegacyText(s)));
		} else if (args.length < 3) {
			if (args[0].equalsIgnoreCase("On")) {
				if (!(sender.hasPermission(""))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NO_PERMISSION));
				} else if (!(Data.dataModel.serverExists(args[1]))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.SERVER_NOT_FOUND));
				} else {
					Data.dataModel.setWhitelistStatus(args[1], true);
					sender.sendMessage(TextComponent.fromLegacyText(Messages.WHITELIST_ENABLED));
				}
			} else if (args[0].equalsIgnoreCase("Off")) {
				if (!(sender.hasPermission(""))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NO_PERMISSION));
				} else if (!(Data.dataModel.serverExists(args[1]))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.SERVER_NOT_FOUND));
				} else {
					Data.dataModel.setWhitelistStatus(args[1], false);
					sender.sendMessage(TextComponent.fromLegacyText(Messages.WHITELIST_DISABLED));
				}
			} else if (args[0].equalsIgnoreCase("List")) {
				if (!(sender.hasPermission(""))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NO_PERMISSION));
				} else if (!(Data.dataModel.serverExists(args[1]))){
					sender.sendMessage(TextComponent.fromLegacyText(Messages.SERVER_NOT_FOUND));
				} else {
					Data.dataModel.getWhitelistPlayerList(args[1]).forEach(s -> sender.sendMessage(TextComponent.fromLegacyText(s)));
				}
			} else if (args[0].equalsIgnoreCase("Add")) {
				if (!(sender.hasPermission(""))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NO_PERMISSION));
				} else {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NOT_ENOUGH_ARGS));
				}
			} else if (args[0].equalsIgnoreCase("Remove")) {
				if (!(sender.hasPermission(""))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NO_PERMISSION));
				} else {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NOT_ENOUGH_ARGS));
				}
			} else {
				Messages.USAGE_MESSAGE.forEach(s -> sender.sendMessage(TextComponent.fromLegacyText(s)));
			}
		} else if (args.length < 4) {
			if (args[0].equalsIgnoreCase("Add")) {
				if (!(sender.hasPermission(""))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NO_PERMISSION));
				} else if (!(Data.dataModel.serverExists(args[1]))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.SERVER_NOT_FOUND));
				} else if (Data.dataModel.playerExistsInWhitelist(args[1], args[2])){
					sender.sendMessage(TextComponent.fromLegacyText(Messages.PLAYER_ALREADY_EXISTS));
				} else {
					Data.dataModel.addPlayerToWhitelist(args[1], args[2]);
					sender.sendMessage(TextComponent.fromLegacyText(Messages.PLAYER_ADDED));
				}
			} else if (args[0].equalsIgnoreCase("Remove")) {
				if (!(sender.hasPermission(""))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.NO_PERMISSION));
				} else if (!(Data.dataModel.serverExists(args[1]))) {
					sender.sendMessage(TextComponent.fromLegacyText(Messages.SERVER_NOT_FOUND));
				} else if (Data.dataModel.playerExistsInWhitelist(args[1], args[2])){
					sender.sendMessage(TextComponent.fromLegacyText(Messages.PLAYER_ISNOT_WHITELISTED));
				} else {
					Data.dataModel.removePlayerFromWhitelist(args[1], args[2]);
					sender.sendMessage(TextComponent.fromLegacyText(Messages.PLAYER_REMOVED));
				}
			}
		} else {
			Messages.USAGE_MESSAGE.forEach(s -> sender.sendMessage(TextComponent.fromLegacyText(s)));
		}
	}
}
