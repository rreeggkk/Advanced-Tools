package rreeggkk.github.io.advancedTools.common.blocks;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class BasicBlock extends Block {

	public BasicBlock(Material material, String unLocName, String textName) {
		this(material, unLocName, textName, 3f, 5f);
	}
	public BasicBlock(Material material, String unLocName, String textName, float hardness, float resist) {
		super(material);
		setBlockName(unLocName);
		setBlockTextureName("advancedtools:" + textName);
		setCreativeTab(AdvancedTools.instance.cTab);
		setHardness(hardness);
		setResistance(resist);
	}
	
	public BasicBlock setHarvestLvl(String toolClass, int level) {
		super.setHarvestLevel(toolClass, level);
		return this;
	}
	public BasicBlock setHarvestLvl(String toolClass, int level, int metadata) {
		super.setHarvestLevel(toolClass, level, metadata);
		return this;
	}
}
