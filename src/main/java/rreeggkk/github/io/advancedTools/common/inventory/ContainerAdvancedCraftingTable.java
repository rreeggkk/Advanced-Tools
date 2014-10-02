package rreeggkk.github.io.advancedTools.common.inventory;

import rreeggkk.github.io.advancedTools.AdvancedTools;
import rreeggkk.github.io.advancedTools.common.crafting.AdvancedCraftingTableHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerAdvancedCraftingTable extends Container {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 9, 9);
	public IInventory craftResult = new InventoryCraftResult();
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public ContainerAdvancedCraftingTable(InventoryPlayer inv, World w, int xp, int yp, int zp) {
		int craftOffx = -25;
		int craftOffy = -12;


		int playerInvOffX = -3;
		int playerInvOffY = 102;

		worldObj = w; posX = xp; posY = yp; posZ = zp;

		this.addSlotToContainer(new SlotCrafting(inv.player, this.craftMatrix, this.craftResult, 0, 204, 77));

		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++) {
				this.addSlotToContainer(new Slot(this.craftMatrix, y + x * 9, 30 + y * 18 + craftOffx, 17 + x * 18 + craftOffy));
			}
		}


		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(inv, x, 8 + 18 * x + playerInvOffX, 130 + playerInvOffY));
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(inv, x + (y * 9) + 9, 8 + 18 * x + playerInvOffX, 72 + y * 18 + playerInvOffY));
			}
		}

		onCraftMatrixChanged(craftMatrix);
	}
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.worldObj.getBlock(this.posX, this.posY, this.posZ) != AdvancedTools.instance.aCTable ? false : player.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
	}

	@Override
	public void onCraftMatrixChanged(IInventory inv) {
		this.craftResult.setInventorySlotContents(0, null);

		if (inv != craftMatrix) {
			return;
		}

		boolean anySlot = false;
		int minx,miny,maxx,maxy;
		minx = 8; miny = 8; maxx = 0; maxy = 0;

		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++) {
				if (craftMatrix.getStackInSlot(y + x * 9) != null) {
					minx = Math.min(x, minx);
					miny = Math.min(y, miny);

					maxx = Math.max(x, maxx);
					maxy = Math.max(y, maxy);

					anySlot = true;
				}
			}
		}

		if (!anySlot) {
			return;
		}

		int usedW, usedH;
		usedW = maxx - minx;
		usedH = maxy - miny;

		InventoryCrafting clippedCraftingTable = new InventoryCrafting(this, Math.max(usedW, usedH) + 1, Math.max(usedH, usedW) + 1);

		for (int x = 0; x <= Math.max(usedW, usedH); x++){
			for (int y = 0; y <= Math.max(usedW, usedH); y++) {
				clippedCraftingTable.setInventorySlotContents(y + x * (Math.max(usedW, usedH) + 1), craftMatrix.getStackInSlot((y + miny) + (x + minx) * 9));
			}
		}

		ItemStack advancedRecipe = AdvancedCraftingTableHandler.instance.findMatchingRecipe(clippedCraftingTable, this.worldObj);
		if (advancedRecipe != null) {
			this.craftResult.setInventorySlotContents(0, advancedRecipe);
			return;
		}
	
		this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(clippedCraftingTable, this.worldObj));
		return;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!this.worldObj.isRemote)
		{
			for (int i = 0; i < 81; ++i)
			{
				ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

				if (itemstack != null)
				{
					player.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNum)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotNum);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			//Output = 0
			//Crafting = 1-82
			//Hotbar = 82-91
			//Inv = 91-118
			if (slotNum == 0)
			{
				if (!this.mergeItemStack(itemstack1, 82, 118, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (slotNum >= 82 && slotNum < 91)
			{
				if (!this.mergeItemStack(itemstack1, 91, 118, false))
				{
					return null;
				}
			}
			else if (slotNum >= 91 && slotNum < 118)
			{
				if (!this.mergeItemStack(itemstack1, 82, 91, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 82, 118, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	public boolean func_94530_a(ItemStack stack, Slot slot)
	{
		return slot.inventory != this.craftResult && super.func_94530_a(stack, slot);
	}

}
