package ir.ciph3r.grandwhitelist.configuration.data;

import ir.ciph3r.grandwhitelist.configuration.yml.Config;

public class Data {
	public static DataModel dataModel;
	public static Permissions permissions;
	private FileBased fileBased = new FileBased("storage.yml");
	private MySQLBased mySQLBased;

	public void initializeData() {
		if (Config.STORAGE_TYPE.equalsIgnoreCase("File")) {
			fileBased.setup();
			dataModel = fileBased;
		} else if (Config.STORAGE_TYPE.equalsIgnoreCase("MySQL")) {
			mySQLBased.setup();
			dataModel = new MySQLBased();
		}
		permissions.init();
	}
}
