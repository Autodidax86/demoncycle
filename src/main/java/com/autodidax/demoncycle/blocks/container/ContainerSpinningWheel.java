package com.autodidax.demoncycle.blocks.container;

import com.autodidax.demoncycle.blocks.container.slot.SlotSpinningWheelItemHandler;
import com.autodidax.demoncycle.blocks.recipes.SpinningWheelRecipes;
import com.autodidax.demoncycle.blocks.tileentities.TileEntitySpinningWheel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSpinningWheel extends Container 
{
	private final TileEntitySpinningWheel tileEntity;
	private int processTime, currentProcessTime, totalProcessTime;

	public ContainerSpinningWheel(InventoryPlayer player, TileEntitySpinningWheel tileEntity)
	{
		this.tileEntity = tileEntity;
		IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotSpinningWheelItemHandler(handler, 0, 57, 33));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 112, 33));

		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
			}
		}
		
		for(int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.currentProcessTime != this.tileEntity.getField(0)) listener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
			if(this.processTime != this.tileEntity.getField(1)) listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
			if(this.totalProcessTime != this.tileEntity.getField(2)) listener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
		}
		
		this.currentProcessTime = this.tileEntity.getField(0);
		this.processTime = this.tileEntity.getField(1);
		this.totalProcessTime = this.tileEntity.getField(2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) 
	{
		this.tileEntity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileEntity.isUsableByPlayer(playerIn);
	}
}
