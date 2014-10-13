package rreeggkk.github.io.advancedTools;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import rreeggkk.github.io.advancedTools.client.creativetabs.CustomCreativeTab;
import rreeggkk.github.io.advancedTools.common.blocks.AdvancedBlocks;
import rreeggkk.github.io.advancedTools.common.blocks.BasicBlock;
import rreeggkk.github.io.advancedTools.common.blocks.BlockAdvancedCraftingTable;
import rreeggkk.github.io.advancedTools.common.config.ConfigurationHandler;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
import rreeggkk.github.io.advancedTools.common.gui.GuiHandler;
import rreeggkk.github.io.advancedTools.common.item.AdvancedItems;
import rreeggkk.github.io.advancedTools.common.item.BasicItem;
import rreeggkk.github.io.advancedTools.common.item.tool.ToolMaterials;
import rreeggkk.github.io.advancedTools.common.item.tool.pickaxe.BasicPickaxe;
import rreeggkk.github.io.advancedTools.common.util.OreGenData;
import rreeggkk.github.io.advancedTools.common.world.RreeOreGenerator;
import rreeggkk.github.io.advancedTools.init.BlockBreakLevelModification;
import rreeggkk.github.io.advancedTools.init.CraftingRecipies;
import rreeggkk.github.io.advancedTools.init.RecipieRemoval;
import rreeggkk.github.io.advancedTools.proxy.IProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION, guiFactory = Constants.GUI_FACTORY_CLASS)
public class AdvancedTools {

	//Mod Instance
	@Instance()
	public static AdvancedTools instance;

	//Proxy
	@SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	//Creative Tabs
	public CustomCreativeTab cTab;

	private OreGenData copperOreGen = new OreGenData(),
			zincOreGen = new OreGenData();	
	private RreeOreGenerator oreGenerator = new RreeOreGenerator();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		
		cTab = new CustomCreativeTab("AdvancedTools");

		//Items
		GameRegistry.registerItem(AdvancedItems.flintPick = new BasicPickaxe(ToolMaterials.flint, cTab, "flintPick", "flint_pickaxe"), "flintPick");
		GameRegistry.registerItem(AdvancedItems.ingotCopper = new BasicItem("ingotCopper", "ingotCopper"), "ingotCopper");
		GameRegistry.registerItem(AdvancedItems.ingotZinc = new BasicItem("ingotZinc", "ingotZinc"), "ingotZinc");

		//Blocks
		GameRegistry.registerBlock(AdvancedBlocks.aCTable = new BlockAdvancedCraftingTable(), "advancedCraftingTable");
		GameRegistry.registerBlock(AdvancedBlocks.oreCopper = new BasicBlock(Material.rock, "oreCopper", "oreCopper"), "oreCopper");
		GameRegistry.registerBlock(AdvancedBlocks.oreZinc = new BasicBlock(Material.rock, "oreZinc", "oreZinc"), "oreZinc");

		copperOreGen.setBlock(AdvancedBlocks.oreCopper);
		copperOreGen.setMaxHeight(200);
		copperOreGen.setMinHeight(30);
		copperOreGen.setOrePerVein(14);
		copperOreGen.setVeinPerChunk(20);
		zincOreGen.setBlock(AdvancedBlocks.oreZinc);
		zincOreGen.setMaxHeight(64);
		zincOreGen.setMinHeight(0);
		zincOreGen.setOrePerVein(10);
		zincOreGen.setVeinPerChunk(3);
		oreGenerator.addOreGenToSurface(copperOreGen);
		oreGenerator.addOreGenToSurface(zincOreGen);

		GameRegistry.registerWorldGenerator(oreGenerator, 1);

		//Finishing up
		cTab.setIcon(AdvancedItems.flintPick);
		forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RecipieRemoval.removeToolCraftingRecipies();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		CraftingRecipies.initVanillaCraftingTableRecipes();
		CraftingRecipies.initFurnaceRecipies();
		CraftingRecipies.initAdvancedCraftingTableRecipes();
		CraftingRecipies.initOreDict();

		BlockBreakLevelModification.init();
	}

	private void forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks(){
		ForgeHooks.isToolEffective(new ItemStack(Items.wooden_axe), Blocks.log, 0);
	}
}	
