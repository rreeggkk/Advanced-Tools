package rreeggkk.github.io.advancedTools.client.gui.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.gui.ForgeGuiFactory.ForgeConfigGui.ChunkLoaderEntry;
import net.minecraftforge.client.gui.ForgeGuiFactory.ForgeConfigGui.GeneralEntry;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import rreeggkk.github.io.advancedTools.client.gui.config.entry.OreGenEntry;
import rreeggkk.github.io.advancedTools.common.config.ConfigurationHandler;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
import cpw.mods.fml.client.config.ConfigGuiType;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.GuiConfigEntries.NumberSliderEntry;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;

public class ModGuiConfig extends GuiConfig {
	public ModGuiConfig(GuiScreen guiScreen) {
		super(guiScreen,
				getConfigElements(),
				Constants.MODID,
				Constants.MODID,
				false,
				false,
				I18n.format("advancedTools.configGui.advancedTools")
				);
	}
	
	

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		list.add(new DummyCategoryElement("oreGen", "advancedTools.configGui.advancedToolsOreGen", OreGenEntry.class));

		return list;
	}
}
