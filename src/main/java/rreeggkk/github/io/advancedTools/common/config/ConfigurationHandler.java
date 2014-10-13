package rreeggkk.github.io.advancedTools.common.config;

import java.io.File;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
import rreeggkk.github.io.advancedTools.common.util.OreGenData;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

	public static Configuration config;
	public static OreGenData copperOreGen = new OreGenData();;

	public static void init(File configFile) {
		if (config == null) {
			config = new Configuration(configFile);
		}

		syncConfigs();
	}

	public static void syncConfigs() {
		config.load();

		copperOreGen.setMaxHeight(config.getInt("MaxHeight", "oregen", 200, 0, 255, "The maximum y level that the copper will spawn at"));
		
		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Constants.MODID)) {
			syncConfigs();
		}
	}
}
