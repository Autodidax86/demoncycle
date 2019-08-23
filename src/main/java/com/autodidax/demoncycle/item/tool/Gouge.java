package com.autodidax.demoncycle.item.tool;

import com.autodidax.demoncycle.item.ItemBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class Gouge extends ItemBase {

	public Gouge(ToolMaterial material, String name) {
		super(name);
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses()*10);
		this.setHarvestLevel(material.toString(), 0);
	}

	@Override
	public boolean canItemEditBlocks() {
		return false;
	}
	
	@Override
	public int getItemStackLimit() {
		return 1;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return super.showDurabilityBar(stack) && canBeUsed(stack);
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean canBeUsed(ItemStack stack) {
		int temp = stack.getMaxDamage();
		int temp2 = stack.getItemDamage();
		boolean canBeUsed = temp2 <= temp;
		return canBeUsed;
 }
}
