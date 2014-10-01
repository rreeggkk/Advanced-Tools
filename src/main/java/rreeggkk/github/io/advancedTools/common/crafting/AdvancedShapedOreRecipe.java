package rreeggkk.github.io.advancedTools.common.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class AdvancedShapedOreRecipe extends ShapedOreRecipe {
	
    private static final int MAX_CRAFT_GRID_WIDTH = 9;
    private static final int MAX_CRAFT_GRID_HEIGHT = 9;

	public AdvancedShapedOreRecipe(Block result, Object[] recipe) {
		super(result, recipe);
	}
	public AdvancedShapedOreRecipe(Item result, Object... recipe) {
		super(result, recipe);
	}
	public AdvancedShapedOreRecipe(ItemStack result, Object... recipe) {
		super(result, recipe);
	}	
}
