package rreeggkk.github.io.advancedTools.common.item.tool.pickaxe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class BasicPickaxe extends ItemPickaxe {
	public BasicPickaxe(ToolMaterial toolMaterial, CreativeTabs cTab, String unLocName, String textureLoc) {
		super(toolMaterial);
		setCreativeTab(cTab);
		setUnlocalizedName(unLocName);
		setTextureName(textureLoc);
	}
}
