package rreeggkk.github.io.advancedTools.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

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

}
