package rreeggkk.github.io.advancedTools.common.blocks;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import rreeggkk.github.io.advancedTools.common.constants.GuiIDs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAdvancedCraftingTable extends Block {
	
    @SideOnly(Side.CLIENT)
    private IIcon topTexture;
 
    
	public BlockAdvancedCraftingTable(Material mat) {
		super(mat);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegistry)
	{
		this.blockIcon = iconRegistry.registerIcon(this.getTextureName() + "_side");
		this.topTexture = iconRegistry.registerIcon(this.getTextureName() + "_top");
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.topTexture : (side == 0 ? Blocks.planks.getBlockTextureFromSide(side) : this.blockIcon);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitSide, float hitX, float hitY, float hitZ) {
    	player.openGui(AdvancedTools.instance, GuiIDs.AdvancedCraftingTableGuiID, world, x, y, z);
    	return true;
    }
}
