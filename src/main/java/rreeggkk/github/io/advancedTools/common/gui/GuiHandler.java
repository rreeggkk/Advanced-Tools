package rreeggkk.github.io.advancedTools.common.gui;

import rreeggkk.github.io.advancedTools.client.gui.GuiAdvancedCraftingTable;
import rreeggkk.github.io.advancedTools.common.constants.GuiIDs;
import rreeggkk.github.io.advancedTools.common.inventory.ContainerAdvancedCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiIDs.AdvancedCraftingTableGuiID) {
			return new ContainerAdvancedCraftingTable(player.inventory, world, x, y, z);			
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiIDs.AdvancedCraftingTableGuiID) {
			return new GuiAdvancedCraftingTable(new ContainerAdvancedCraftingTable(player.inventory, world, x,y,z));
		}
		return null;
	}

}
