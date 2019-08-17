package com.autodidax.demoncycle.util;

import com.autodidax.demoncycle.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DemonCycleTab extends CreativeTabs {

	public DemonCycleTab() {
		super("DemonCycle");
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(ModItems.GOUGE_STONE);
	}

}
