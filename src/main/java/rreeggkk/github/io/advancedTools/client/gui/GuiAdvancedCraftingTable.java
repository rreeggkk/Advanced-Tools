package rreeggkk.github.io.advancedTools.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import rreeggkk.github.io.advancedTools.common.inventory.ContainerAdvancedCraftingTable;

public class GuiAdvancedCraftingTable extends GuiContainer {
    private static final ResourceLocation guiTextures = new ResourceLocation("advancedtools:textures/gui/advancedCraftingTable.png");
	private ContainerAdvancedCraftingTable container;
	public int guiLeft;
	public int guiTop;

	public GuiAdvancedCraftingTable(ContainerAdvancedCraftingTable container) {
		super(container);
		this.xSize = 256;
		this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f1, int i1, int i2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiTextures);
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		
	}
}
