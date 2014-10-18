package rreeggkk.github.io.advancedTools.client.gui.config.entry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.ConfigElement;
import rreeggkk.github.io.advancedTools.common.config.ConfigurationHandler;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.GuiConfigEntries.IConfigEntry;
import cpw.mods.fml.client.config.IConfigElement;


public class OreGenEntry extends CategoryEntry {

	public OreGenEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop){
		super(owningScreen, owningEntryList, prop);
	}

	@Override
	protected GuiScreen buildChildScreen() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		list.addAll((new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.CATEGORY_OREGEN))).getChildElements());
		
		return new GuiConfig(this.owningScreen, 
				list, 
				this.owningScreen.modID,
				ConfigurationHandler.CATEGORY_OREGEN,
				true, 
				true,
				I18n.format("advancedTools.configGui.advancedTools"),
				I18n.format("advancedTools.configGui.advancedToolsOreGen"));
	}
	
	
}

