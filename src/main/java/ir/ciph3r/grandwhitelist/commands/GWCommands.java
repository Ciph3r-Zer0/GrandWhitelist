package ir.ciph3r.grandwhitelist.commands;

import ir.ciph3r.grandwhitelist.configuration.yml.Messages;
import ir.ciph3r.grandwhitelist.configuration.yml.Servers;
import ir.ciph3r.grandwhitelist.utilities.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.util.Collection;
import java.util.List;

public class GWCommands extends Command {
	public GWCommands(String name) {
		super("grandwhitelist", null, "gw", "whitelist");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Configuration serversConfig = Servers.getConfig();
		Configuration messagesConfig = Messages.getConfig();

		if (args.length == 0) {
			for (String s : messagesConfig.getStringList("usage")) {
				sender.sendMessage(new TextComponent(Utils.colorize(s)));
				return;
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("on")) {
				String server = args[1];



				Collection<String> CServers = serversConfig.getSection("servers").getKeys();
				if (!(CServers.contains(server))) {
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("server_not_found"))));
				} else {
					serversConfig.set("servers." + server + ".whitelist", true);
					Servers.saveConfig();
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("whitelist_enabled"))));
				}
			}
			if (args[0].equalsIgnoreCase("off")) {
				String server = args[1];

				Collection<String> CServers = serversConfig.getSection("servers").getKeys();
				if (!(CServers.contains(server))) {
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("server_not_found"))));
				} else {
					serversConfig.set("servers." + server + ".whitelist", false);
					Servers.saveConfig();
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("whitelist_disabled"))));
				}
			}
		} else if (args.length == 3) {
			if (args[0].equalsIgnoreCase("add")) {
				String server = args[1];
				String player = args[2];

				Collection<String> CServers = serversConfig.getSection("servers").getKeys();
				if (!(CServers.contains(server))) {
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("server_not_found"))));
				} else {
					List<String> PList = serversConfig.getStringList("servers." + server + ".whitelisted");
					if (PList.contains(player)) {
						sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("player_already_whitelisted"))));
						return;
					}
					PList.add(player);

					serversConfig.set("servers." + server + ".whitelisted", PList);
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("player_added"))));
					Servers.saveConfig();
				}
			}
			if (args[0].equalsIgnoreCase("remove")) {
				String server = args[1];
				String player = args[2];

				Collection<String> CServers = serversConfig.getSection("servers").getKeys();
				if (!(CServers.contains(server))) {
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("server_not_found"))));
				} else {
					List<String> PList = serversConfig.getStringList("servers." + server + ".whitelisted");
					if (!(PList.contains(player))) {
						sender.sendMessage(new TextComponent(Utils.colorize("player_wasnot_whitelisted")));
						return;
					}
					PList.remove(player);

					serversConfig.set("servers." + server + ".whitelisted", PList);
					sender.sendMessage(new TextComponent(Utils.colorize(messagesConfig.getString("player_removed"))));
					Servers.saveConfig();
				}
			}
		}
	}
}
