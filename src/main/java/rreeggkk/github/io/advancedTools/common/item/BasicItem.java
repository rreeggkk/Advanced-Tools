package rreeggkk.github.io.advancedTools.common.item;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import net.minecraft.item.Item;

public class BasicItem extends Item {
	public BasicItem(String unLocName, String textureLoc) {
		setCreativeTab(AdvancedTools.instance.cTab);
		setUnlocalizedName(unLocName);
		setTextureName("advancedtools:" + textureLoc);
	}
}
