package ir.ciph3r.grandwhitelist.configuration.yml;

import java.util.List;

public class Messages extends YmlModel {
	public static List<String> USAGE_MESSAGE;
	public static String SERVER_NOT_FOUND;
	public static String WHITELIST_ENABLED;
	public static String WHITELIST_DISABLED;
	public static String PLAYER_ADDED;
	public static String PLAYER_REMOVED;
	public static String PLAYER_ALREADY_EXISTS;
	public static String PLAYER_ISNOT_WHITELISTED;
	public static String WHITELIST_MESSAGE;
	public static String NO_PERMISSION;
	public static String NOT_ENOUGH_ARGS;

	public Messages() {
		super("messages.yml");
	}

	@Override
	public void init() {
		USAGE_MESSAGE = getConfig().getStringList("usage_command");
		SERVER_NOT_FOUND = getConfig().getString("server_not_found");
		WHITELIST_ENABLED = getConfig().getString("whitelist_enabled");
		WHITELIST_DISABLED = getConfig().getString("whitelist_disabled");
		PLAYER_ADDED = getConfig().getString("player_added");
		PLAYER_REMOVED = getConfig().getString("player_removed");
		PLAYER_ALREADY_EXISTS = getConfig().getString("player_already_whitelisted");
		PLAYER_ISNOT_WHITELISTED = getConfig().getString("player_isnot_whitelisted");
		WHITELIST_MESSAGE = getConfig().getString("whitelist_message");
		NO_PERMISSION = getConfig().getString("no_permission");
		NOT_ENOUGH_ARGS = getConfig().getString("not_enough_args");
	}
}
