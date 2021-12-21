package ir.ciph3r.grandwhitelist.configuration.yml;

public class Config extends YmlModel{
	public static String STORAGE_TYPE;

	public Config() {
		super("config.yml");
	}

	@Override
	public void init() {
		STORAGE_TYPE = getConfig().getString("storage_type");
	}
}
