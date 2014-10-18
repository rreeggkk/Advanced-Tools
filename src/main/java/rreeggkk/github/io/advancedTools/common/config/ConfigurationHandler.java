package rreeggkk.github.io.advancedTools.common.config;

import java.io.File;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import rreeggkk.github.io.advancedTools.common.blocks.AdvancedBlocks;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
import rreeggkk.github.io.advancedTools.common.util.OreGenData;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	
	public static final String CATEGORY_OREGEN = "oregen";
	public static final String CATEGORY_OREGEN_COPPER = "oregen.Copper";
	public static final String CATEGORY_OREGEN_ZINC = "oregen.Zinc";
	public static final String CATEGORY_OREGEN_TIN = "oregen.Tin";
	public static final String CATEGORY_OREGEN_NICKEL = "oregen.Nickel";

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
		data.setMaxHeight(config.getInt("MaxHeight", category, data.getMaxHeight(), 0, 255, "The maximum y level that the ore will spawn at"));
		data.setMinHeight(config.getInt("MinHeight", category, data.getMinHeight(), 0, 255, "The minimum y level that the ore will spawn at"));
		data.setOrePerVein(config.getInt("Ore per Vien", category, data.getOrePerVein(), 0, 1000, "The maximum amount of ores per vein"));
		data.setVeinPerChunk(config.getInt("Viens per Chunk", category, data.getVeinPerChunk(), 0, 1000, "The aproximate amount of veins per chunk"));
	}
	private static void loadDefaultOreGenData() {
		copperOreGen = new OreGenData(200, 40, 14, 20, AdvancedBlocks.oreCopper);
		zincOreGen = new OreGenData(100, 50, 24, 10, AdvancedBlocks.oreZinc);
		tinOreGen = new OreGenData(120, 30, 8, 16, AdvancedBlocks.oreZinc); //TODO: ADD ORE + FIX BLOCK
		nickelOreGen = new OreGenData(50, 16, 9, 6, AdvancedBlocks.oreZinc); //TODO: ADD ORE + FIX BLOCK
	}
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Constants.MODID)) {
			syncConfigs();
		}
	}
}
