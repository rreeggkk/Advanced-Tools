package rreeggkk.github.io.advancedTools.common.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AdvancedShapedRecipes implements IRecipe {
	   /** How many horizontal slots this recipe is wide. */
    public final int recipeWidth;
    /** How many vertical slots this recipe uses. */
    public final int recipeHeight;
    /** Is a array of ItemStack that composes the recipe. */
    public final ItemStack[] recipeItems;
    /** Is the ItemStack that you get when craft the recipe. */
    private ItemStack recipeOutput;
    private boolean field_92101_f;
    private static final String __OBFID = "CL_00000093";

    public AdvancedShapedRecipes(int width, int height, ItemStack[] inputs, ItemStack output){
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = inputs;
        this.recipeOutput = output;
    }

    public ItemStack getRecipeOutput(){
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv, World world){
        for (int i = 0; i <= 9 - this.recipeWidth; ++i){
            for (int j = 0; j <= 9 - this.recipeHeight; ++j){
                if (this.checkMatch(inv, i, j, true)){
                    return true;
                }

                if (this.checkMatch(inv, i, j, false)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting inv, int f1, int f2, boolean bool){
        for (int k = 0; k < 9; ++k){
            for (int l = 0; l < 9; ++l){
                int i1 = k - f1;
                int j1 = l - f2;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight){
                    if (bool){
                        itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
                    }else{
                        itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
                    }
                }

                ItemStack itemstack1 = inv.getStackInRowAndColumn(k, l);

                if (itemstack1 != null || itemstack != null){
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
                        return false;
                    }

                    if (itemstack.getItem() != itemstack1.getItem()){
                        return false;
                    }

                    if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage()){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv){
        ItemStack itemstack = this.getRecipeOutput().copy();

        if (this.field_92101_f){
            for (int i = 0; i < inv.getSizeInventory(); ++i){
                ItemStack itemstack1 = inv.getStackInSlot(i);

                if (itemstack1 != null && itemstack1.hasTagCompound()){
                    itemstack.setTagCompound((NBTTagCompound)itemstack1.stackTagCompound.copy());
                }
            }
        }

        return itemstack;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize(){
        return this.recipeWidth * this.recipeHeight;
    }

    public AdvancedShapedRecipes func_92100_c(){
        this.field_92101_f = true;
        return this;
    }
}
