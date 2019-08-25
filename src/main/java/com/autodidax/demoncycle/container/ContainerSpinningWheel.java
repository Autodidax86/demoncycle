package com.autodidax.demoncycle.container;

import com.autodidax.demoncycle.container.slot.SlotSpinningWheelItemHandler;
import com.autodidax.demoncycle.recipe.SpinningWheelRecipes;
import com.autodidax.demoncycle.tileentity.TileEntitySpinningWheel;

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
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(par2);


		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 1)
			{
				if (!mergeItemStack(itemstack1, 2, 38, true))
					return ItemStack.EMPTY;

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 1 && par2 != 0)
			{
				if(SpinningWheelRecipes.getInstance().getSpinningResult(itemstack1) != ItemStack.EMPTY) {
					if (!mergeItemStack(itemstack1, 0, 2, false))
						return ItemStack.EMPTY;
				} else if (par2 >= 2 && par2 < 29){
					if (!mergeItemStack(itemstack1, 29, 38, false))
						return ItemStack.EMPTY;
				}
				else if (par2 >= 29 && par2 < 38 && !mergeItemStack(itemstack1, 2, 29, false))
					return ItemStack.EMPTY;
			}
			else if (!mergeItemStack(itemstack1, 2, 38, false))
				return ItemStack.EMPTY;

			if (itemstack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
}
