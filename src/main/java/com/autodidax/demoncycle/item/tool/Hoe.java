package com.autodidax.demoncycle.item.tool;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.IHasModel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;

public class Hoe extends ItemHoe implements IHasModel{

	public Hoe(String name, ToolMaterial material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.DemonCycleTab);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "Inventory");
	}
}
