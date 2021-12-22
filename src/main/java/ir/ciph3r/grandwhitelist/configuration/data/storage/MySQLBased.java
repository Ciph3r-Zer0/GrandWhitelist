package ir.ciph3r.grandwhitelist.configuration.data.storage;

import java.util.Collection;
import java.util.List;

public class MySQLBased implements DataModel {
	@Override
	public void setup() {

	}

	@Override
	public void init() {

	}

	@Override
	public Collection<String> getServerList() {
		return null;
	}

	@Override
	public List<String> getWhitelistPlayerList(String serverName) {
		return null;
	}

	@Override
	public boolean isWhitelisted(String serverName) {
		return false;
	}

	@Override
	public boolean serverExists(String serverName) {
		return false;
	}

	@Override
	public boolean playerExistsInWhitelist(String serverName, String playerName) {
		return false;
	}

	@Override
	public void setWhitelistStatus(String serverName, boolean status) {

	}

	@Override
	public void addPlayerToWhitelist(String serverName, String playerName) {

	}

	@Override
	public void removePlayerFromWhitelist(String serverName, String playerName) {

	}
	//TODO: to be done
}