package com.autodidax.demoncycle.container.slot;

import com.autodidax.demoncycle.tileentity.TileEntitySpinningWheel;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SlotSpinningWheelItemHandler extends SlotItemHandler {

	public SlotSpinningWheelItemHandler(IItemHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntitySpinningWheel.isItemValid(stack);
	}
}
