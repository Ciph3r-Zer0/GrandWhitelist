package ir.ciph3r.grandwhitelist.configuration.data;

import java.util.Collection;
import java.util.List;

public interface DataModel {
	public void setup();
	public void init();

	public Collection<String> getServerList();
	public List<String> getWhitelistPlayerList(String serverName);
	public boolean isWhitelisted(String serverName);
	public boolean serverExists(String serverName);
	public boolean playerExistsInWhitelist(String serverName, String playerName);

	public void setWhitelistStatus(String serverName, boolean status);
	public void addPlayerToWhitelist(String serverName, String playerName);
	public void removePlayerFromWhitelist(String serverName, String playerName);
}
