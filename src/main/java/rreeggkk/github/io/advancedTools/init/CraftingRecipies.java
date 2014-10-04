package rreeggkk.github.io.advancedTools.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import rreeggkk.github.io.advancedTools.common.blocks.AdvancedBlocks;
import rreeggkk.github.io.advancedTools.common.crafting.AdvancedCraftingTableHandler;
import rreeggkk.github.io.advancedTools.common.crafting.AdvancedShapedOreRecipe;
import rreeggkk.github.io.advancedTools.common.item.AdvancedItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipies {
	public static void initVanillaCraftingTableRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(AdvancedBlocks.aCTable, new Object[] {
				"ccc",
				"ccc",
				"ccc",
				'c', new ItemStack(Blocks.crafting_table)
		}));
	}
	public static void initAdvancedCraftingTableRecipes() {
		addAdvancedPickRecipe(new ItemStack(AdvancedItems.flintPick), new ItemStack(Items.flint), new ItemStack(Items.stick), true);
		addAdvancedPickRecipe(new ItemStack(Items.stone_pickaxe), new ItemStack(Blocks.cobblestone), new ItemStack(Items.stick), true);
	}
	private static void addAdvancedPickRecipe(ItemStack output, ItemStack headMat, ItemStack rodMat, boolean ore) {
		if (ore) {
			AdvancedCraftingTableHandler.instance.addRecipe(new AdvancedShapedOreRecipe(output, 
					" hhh ",
					"h r h",
					"  r  ",
					"  r  ",
					"  r  ",
					'h', headMat,
					'r', rodMat
					));
		} else {
			AdvancedCraftingTableHandler.instance.addShapedRecipe(output, 
					" hhh ",
					"h r h",
					"  r  ",
					"  r  ",
					"  r  ",
					'h', headMat,
					'r', rodMat
					);
		}
	}
	public static void initOreDict() {
		OreDictionary.registerOre("oreCopper", AdvancedBlocks.oreCopper);
		OreDictionary.registerOre("oreZinc", AdvancedBlocks.oreZinc);
	}
	public static void initFurnaceRecipies() {
		GameRegistry.addSmelting(AdvancedBlocks.oreCopper, new ItemStack(AdvancedItems.ingotCopper), 0.5f);
	}
}
