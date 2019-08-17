package com.autodidax.demoncycle.items;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.IHasModel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ItemSeed extends ItemSeeds implements IHasModel {

	public ItemSeed(String name, Block crops) {
		super(crops, Blocks.FARMLAND);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.DemonCycleTab);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
		Main.proxy.registerItemRenderer(this, 0, "Inventory");
	}
}
