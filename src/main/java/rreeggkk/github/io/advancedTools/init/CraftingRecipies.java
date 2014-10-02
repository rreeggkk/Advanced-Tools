package rreeggkk.github.io.advancedTools.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import rreeggkk.github.io.advancedTools.AdvancedTools;
import rreeggkk.github.io.advancedTools.common.crafting.AdvancedCraftingTableHandler;

public class CraftingRecipies {
	public static void initVanillaCraftingTableRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(AdvancedTools.instance.aCTable, new Object[] {
				"ccc",
				"ccc",
				"ccc",
				'c', new ItemStack(Blocks.crafting_table)
		}));
	}

	public static void initAdvancedCraftingTableRecipes() {
		addAdvancedPickRecipe(new ItemStack(AdvancedTools.instance.flintPick), new ItemStack(Items.flint), new ItemStack(Items.stick), true);
		addAdvancedPickRecipe(new ItemStack(Items.stone_pickaxe), new ItemStack(Blocks.cobblestone), new ItemStack(Items.stick), true);
	}

	private static void addAdvancedPickRecipe(ItemStack output, ItemStack headMat, ItemStack rodMat, boolean ore) {
		if (ore) {
			AdvancedCraftingTableHandler.instance.addRecipe(new ShapedOreRecipe(output, 
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
}
