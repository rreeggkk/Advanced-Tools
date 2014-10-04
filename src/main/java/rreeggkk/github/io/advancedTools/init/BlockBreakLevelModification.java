package rreeggkk.github.io.advancedTools.init;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import rreeggkk.github.io.advancedTools.common.blocks.AdvancedBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BlockBreakLevelModification {
	
	private static Block[] lvl0 = {}; //Stone
	private static Block[] lvl1 = {Blocks.coal_ore, AdvancedBlocks.oreCopper, AdvancedBlocks.oreZinc}; //Copper, Zinc
	private static Block[] lvl2 = {}; //Tin, Nickel
	private static Block[] lvl3 = {}; //Iron, Tungsten, Aluminium
	private static Block[] lvl4 = {}; //Titanium, Silver, Gold, Platinum
	//private static Block[] lvl5 = {}; //Diamond, Cobalt, Chromium
	//private static Block[] lvl6 = {}; //(Alloy: Stellite), molybdenum, obsidian
	//private static Block[] lvl7 = {}; //(Alloy: Ultimet)
	//private static Block[] lvl8 = {}; //
	//private static Block[] lvl9 = {}; //
	//private static Block[] lvl10 = {}; //
	
	
	private static Block[][] arrs = new Block[][] {lvl0, lvl1, lvl2, lvl3, lvl4};

	public static void init() {
		for (int lvl = 0; lvl < arrs.length; lvl++) {
			if (arrs[lvl] == null) {
				continue;
			}
			for (Block b : arrs[lvl]) {
				b.setHarvestLevel((b.getHarvestTool(0) != null ? b.getHarvestTool(0) : "pickaxe"), lvl, 0);
			}
		}
	}
}
