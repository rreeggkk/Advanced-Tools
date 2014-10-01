package rreeggkk.github.io.advancedTools;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;
import rreeggkk.github.io.advancedTools.client.creativetabs.CustomCreativeTab;
import rreeggkk.github.io.advancedTools.common.blocks.BlockAdvancedCraftingTable;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
import rreeggkk.github.io.advancedTools.common.crafting.AdvancedCraftingTableHandler;
import rreeggkk.github.io.advancedTools.common.crafting.AdvancedShapelessOreRecipe;
import rreeggkk.github.io.advancedTools.common.gui.GuiHandler;
import rreeggkk.github.io.advancedTools.common.item.tool.ToolMaterials;
import rreeggkk.github.io.advancedTools.common.item.tool.pickaxe.BasicPickaxe;
import rreeggkk.github.io.advancedTools.common.util.RecipieRemovalUtil;
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

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION, dependencies = "required-after:Forge")
public class AdvancedTools {

	//Mod Instance
	@Instance()
	public static AdvancedTools instance;

	//Proxy
	@SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	//Creative Tabs
	public CustomCreativeTab cTab;

	//Items
	BasicPickaxe flintPick;
	
	//Blocks
	public BlockAdvancedCraftingTable aCTable;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		cTab = new CustomCreativeTab("AdvancedTools");

		//Items
		GameRegistry.registerItem(flintPick = new BasicPickaxe(ToolMaterials.flint, cTab, "flintPick", "advancedtools:flint_pickaxe"), "flintPick");
		
		//Blocks
		aCTable = new BlockAdvancedCraftingTable(Material.wood);
		aCTable.setBlockName("advancedCraftingTable");
		aCTable.setHardness(2.5f);
		aCTable.setHarvestLevel("axe", 0);
		aCTable.setCreativeTab(cTab);
		aCTable.setBlockTextureName("advancedTools:advancedCraftingTable");
		GameRegistry.registerBlock(aCTable, "advancedCraftingTable");
		
		//Finishing up
		cTab.setIcon(flintPick);
		forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RecipieRemovalUtil.removeToolCraftingRecipies();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		AdvancedCraftingTableHandler.instance.addShapedRecipe(new ItemStack(flintPick), 
				" fff ",
				"f s f",
				"  s  ",
				"  s  ",
				"  s  ",
				'f', new ItemStack(Items.flint),
				's', new ItemStack(Items.stick)
				);
		
		System.out.println("Boop " + OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(Blocks.planks))));
		
		AdvancedShapelessOreRecipe s = new AdvancedShapelessOreRecipe(aCTable, new Object[] {"plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood", "plankWood"});
		AdvancedCraftingTableHandler.instance.addRecipe(s);
		//AdvancedCraftingTableHandler.instance.addRecipe(new ItemStack(aCTable), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks));
	}

	private void forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks(){
		ForgeHooks.isToolEffective(new ItemStack(Items.wooden_axe), Blocks.log, 0);
	}
}	
