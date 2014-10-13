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
				false,
				false,
				I18n.format("advancedTools.configGui.advancedTools")
				);
	}
	
    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        
        /*
        ArrayList<IConfigElement> oreGenList = new ArrayList<IConfigElement>();
        
        //oreGenList.addAll(new ConfigElement(ConfigurationHandler.config.getCategory("OreGen")).getChildElements());
        oreGenList.add((new DummyConfigElement<Integer>("sliderInteger", 200, ConfigGuiType.INTEGER, "advancedTools.configGui.advancedToolsOreGen.Copper.MaxH")).setCustomListEntryClass(NumberSliderEntry.class));
        
        list.add(new DummyCategoryElement("lists", "advancedTools.configGui.advancedToolsOreGen", oreGenList));
        */
        
        list.add(new DummyCategoryElement("advancedTools", "advancedTools.configGui.advancedToolsOreGen", OreGenEntry.class));
        
        return list;
	}
    
    public class OreGenEntry extends CategoryEntry {

        public OreGenEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop){
            super(owningScreen, owningEntryList, prop);
        }
		
		@Override
		protected GuiScreen buildChildScreen() {
            return new GuiConfig(this.owningScreen, 
                    (new ConfigElement(ConfigurationHandler.config.getCategory("oregen"))).getChildElements(), 
                    this.owningScreen.modID,
                    "oregen",
                    this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, 
                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                    GuiConfig.getAbridgedConfigPath(ForgeModContainer.getConfig().toString()),
                    I18n.format("advancedTools.configGui.advancedToolsOreGen"));
		}
    }
    
}
