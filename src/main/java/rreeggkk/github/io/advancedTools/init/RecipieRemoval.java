package rreeggkk.github.io.advancedTools.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class RecipieRemoval {
    private static ArrayList<Item> tools = new ArrayList<Item>(Arrays.asList(new Item[] {Items.wooden_pickaxe,
    		Items.stone_pickaxe, Items.iron_pickaxe, Items.diamond_pickaxe, Items.golden_pickaxe, Items.wooden_shovel,
    		Items.stone_shovel, Items.iron_shovel, Items.diamond_shovel, Items.golden_shovel, Items.wooden_axe, Items.stone_axe,
    		Items.iron_axe, Items.diamond_axe, Items.golden_axe, Items.wooden_hoe, Items.stone_hoe, Items.iron_hoe,
    		Items.diamond_hoe, Items.golden_hoe, Items.wooden_sword, Items.stone_sword, Items.iron_sword, Items.diamond_sword,
    		Items.golden_sword}));

    public static void removeToolCraftingRecipies() {
    	Iterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().iterator();

    	while (iterator.hasNext())
    	{
    	    IRecipe recipe = iterator.next();
    	    if (recipe == null)
    	   	 continue;
    	    ItemStack output = recipe.getRecipeOutput();
    	    if (output != null && output.getItem() != null && tools.contains(output.getItem()))
    		    iterator.remove();
    	}
	}
}
