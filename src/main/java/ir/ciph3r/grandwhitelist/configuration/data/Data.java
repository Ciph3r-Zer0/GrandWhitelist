package ir.ciph3r.grandwhitelist.configuration.data;

import ir.ciph3r.grandwhitelist.configuration.data.storage.DataModel;
import ir.ciph3r.grandwhitelist.configuration.data.storage.FileBased;
import ir.ciph3r.grandwhitelist.configuration.data.storage.MySQLBased;
import ir.ciph3r.grandwhitelist.configuration.data.storage.Permissions;
import ir.ciph3r.grandwhitelist.configuration.yml.Config;
import org.yaml.snakeyaml.error.YAMLException;

public class Data {
	public static DataModel dataModel;
	public static Permissions permissions = new Permissions();
	private FileBased fileBased = new FileBased("storage.yml");
	private MySQLBased mySQLBased;

	public void initializeData() {
		if (Config.STORAGE_TYPE.equalsIgnoreCase("File")) {
			fileBased.setup();
			dataModel = fileBased;
		} else if (Config.STORAGE_TYPE.equalsIgnoreCase("MySQL")) {
			mySQLBased.setup();
			dataModel = new MySQLBased();
		} else {
			throw new YAMLException("Storage type can only be File or MySQL");
		}
		permissions.init();
	}
}
