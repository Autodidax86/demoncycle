package com.autodidax.demoncycle.blocks.Gui;

import com.autodidax.demoncycle.blocks.container.ContainerSpinningWheel;
import com.autodidax.demoncycle.blocks.tileentities.TileEntitySpinningWheel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSpinningWheel extends GuiContainer 
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/spinning_wheel.png");
	private final InventoryPlayer player;
	private final TileEntitySpinningWheel tileentity;
	
	public GuiSpinningWheel(InventoryPlayer player, TileEntitySpinningWheel tileentity) 
	{
		super(new ContainerSpinningWheel(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		int l = this.getSpinningProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 36, 176, 14, l + 1, 16);
	}
	
	private int getSpinningProgressScaled(int pixels)
	{
		int i = this.tileentity.getField(0);
		int j = this.tileentity.getField(1);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
