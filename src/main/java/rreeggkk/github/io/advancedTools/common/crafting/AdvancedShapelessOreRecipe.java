package rreeggkk.github.io.advancedTools.common.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class AdvancedShapelessOreRecipe extends ShapelessOreRecipe {
	
    private static final int MAX_CRAFT_GRID_WIDTH = 9;
    private static final int MAX_CRAFT_GRID_HEIGHT = 9;

	public AdvancedShapelessOreRecipe(Block result, Object[] recipe) {
		super(result, recipe);
	}
	public AdvancedShapelessOreRecipe(Item result, Object... recipe) {
		super(result, recipe);
	}
	public AdvancedShapelessOreRecipe(ItemStack result, Object... recipe) {
		super(result, recipe);
	}	
}
