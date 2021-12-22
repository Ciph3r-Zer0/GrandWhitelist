package ir.ciph3r.grandwhitelist.utilities;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Utils {

	private static String colorize(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static void sendMessage(CommandSender sender, String msg) {
		sender.sendMessage(new TextComponent(colorize(msg)));
	}
	public static void sendMessage(ProxiedPlayer player, String msg) {
		player.sendMessage(new TextComponent(colorize(msg)));
	}
}
