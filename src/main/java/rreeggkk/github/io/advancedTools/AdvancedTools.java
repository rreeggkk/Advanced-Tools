package rreeggkk.github.io.advancedTools;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import rreeggkk.github.io.advancedTools.client.creativetabs.CustomCreativeTab;
import rreeggkk.github.io.advancedTools.common.blocks.BlockAdvancedCraftingTable;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
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
	BlockAdvancedCraftingTable aCTable;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		cTab = new CustomCreativeTab("Advanced Tools");

		//Items
		GameRegistry.registerItem(flintPick = new BasicPickaxe(ToolMaterials.flint, cTab, "flintPick", "advancedtools:flint_pickaxe"), "flintPick");
		
		//Blocks
		aCTable = new BlockAdvancedCraftingTable(Material.wood);
		aCTable.setBlockName("advancedCraftingTable");
		aCTable.setHardness(2f);
		aCTable.setHarvestLevel("axe", 0);
		aCTable.setCreativeTab(cTab);
		aCTable.setBlockTextureName("advancedTools:advancedCraftingTable");
		
		//Finishing up
		cTab.setIcon(flintPick);
		forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RecipieRemovalUtil.removeToolCraftingRecipies();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

	private void forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks(){
		ForgeHooks.isToolEffective(new ItemStack(Items.wooden_axe), Blocks.log, 0);
	}
}	
