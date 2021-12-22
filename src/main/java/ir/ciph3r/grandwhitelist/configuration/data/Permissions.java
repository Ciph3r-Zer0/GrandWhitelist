package ir.ciph3r.grandwhitelist.configuration.data;

public class Permissions {
	public String ENABLE_WHITELIST;
	public String DISABLE_WHITELIST;
	public String ADD_TO_WHITELIST;
	public String REMOVE_FROM_WHITELIST;
	public String LIST;

	public void init() {
		ENABLE_WHITELIST = "grandwhitelist.commands.enable";
		DISABLE_WHITELIST = "grandwhitelist.commands.disable";
		ADD_TO_WHITELIST = "grandwhitelist.commands.add";
		REMOVE_FROM_WHITELIST = "grandwhitelist.commands.remove";
		LIST = "grandwhitelist.commands.list";
	}
}
