package rreeggkk.github.io.advancedTools.common.config;

import java.io.File;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import rreeggkk.github.io.advancedTools.common.blocks.AdvancedBlocks;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
import rreeggkk.github.io.advancedTools.common.util.OreGenData;
import rreeggkk.github.io.advancedTools.common.util.OreGenData.EnabledType;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	
	public static final String langKeyBase = "advancedTools.configGui.advancedToolsOreGen.";
	
	public static final String CATEGORY_OREGEN = "oregen";
	public static final String CATEGORY_OREGEN_COPPER = "copper";
	public static final String CATEGORY_OREGEN_ZINC = "zinc";
	public static final String CATEGORY_OREGEN_TIN = "tin";
	public static final String CATEGORY_OREGEN_NICKEL = "nickel";

	public static Configuration config;
	public static OreGenData copperOreGen, zincOreGen, tinOreGen, nickelOreGen;

	public static void init(File configFile) {
		if (config == null) {
			config = new Configuration(configFile);
			
			loadDefaultOreGenData();
			
			syncConfigs();
			
		}
	}
	private static void syncConfigs() {

		addOreGenDataToConfig(CATEGORY_OREGEN_COPPER, copperOreGen);
		addOreGenDataToConfig(CATEGORY_OREGEN_ZINC, zincOreGen);
		addOreGenDataToConfig(CATEGORY_OREGEN_TIN, tinOreGen);
		addOreGenDataToConfig(CATEGORY_OREGEN_NICKEL, nickelOreGen);
		
		config.save();
	}	
	private static void addOreGenDataToConfig(String category, OreGenData data) {
		data.setEnabledType(EnabledType.valueOf(config.getString("EnabledMode", CATEGORY_OREGEN + "." + category, data.getEnabledType().toString(),
				"The conditions that will allow the ore to spawn. Disabled = never, Dynamic = if needed, Always = always",
				OreGenData.EnabledType.getStringValues(), langKeyBase + category + ".EnabledMode")));
		data.setMaxHeight(config.getInt("MaxHeight", CATEGORY_OREGEN + "." + category, data.getMaxHeight(), 0, 255,
				"The maximum y level that the ore will spawn at", langKeyBase + category + ".MaxHeight"));
		data.setMinHeight(config.getInt("MinHeight", CATEGORY_OREGEN + "." + category, data.getMinHeight(), 0, 255,
				"The minimum y level that the ore will spawn at", langKeyBase + category + ".MinHeight"));
		data.setOrePerVein(config.getInt("Ore per Vien", CATEGORY_OREGEN + "." + category, data.getOrePerVein(), 0, 1000,
				"The maximum amount of ores per vein", langKeyBase + category + ".OrePerVein"));
		data.setVeinPerChunk(config.getInt("Viens per Chunk", CATEGORY_OREGEN + "." + category, data.getVeinPerChunk(), 0, 1000,
				"The aproximate amount of veins per chunk", langKeyBase + category + ".VeinPerChunk"));
	}
	private static void loadDefaultOreGenData() {
		copperOreGen = new OreGenData(200, 40, 14, 10, AdvancedBlocks.oreCopper);
		zincOreGen = new OreGenData(100, 50, 24, 5, AdvancedBlocks.oreZinc);
		tinOreGen = new OreGenData(120, 30, 8, 8, AdvancedBlocks.oreTin); 
		nickelOreGen = new OreGenData(50, 16, 9, 3, AdvancedBlocks.oreNickel);
	}
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Constants.MODID)) {
			syncConfigs();
			AdvancedTools.instance.doInitOreGen();
		}
	}
}
