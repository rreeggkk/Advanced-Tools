package rreeggkk.github.io.advancedTools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import rreeggkk.github.io.advancedTools.common.constants.Constants;
import rreeggkk.github.io.advancedTools.common.item.tool.ToolMaterials;
import rreeggkk.github.io.advancedTools.common.item.tool.pickaxe.FlintPickaxe;
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

	@Instance()
	public static AdvancedTools instance;

	@SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
	public static IProxy proxy;


	FlintPickaxe flintPick = new FlintPickaxe(ToolMaterials.flint);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {


		flintPick.setCreativeTab(CreativeTabs.tabTools);
		flintPick.setUnlocalizedName("flintPick");
		flintPick.setTextureName("advancedtools:flint_pickaxe");
		GameRegistry.registerItem(flintPick, "flintPick");

		forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RecipieRemovalUtil.removeToolCraftingRecipies();

		Blocks.iron_ore.setHarvestLevel("pickaxe", 0);
	}

	private void forceablyLoadTheForgeHooksClassSoThatICanTamperWithBlocks(){
		ForgeHooks.isToolEffective(new ItemStack(Items.wooden_axe), Blocks.log, 0);
	}
}	
