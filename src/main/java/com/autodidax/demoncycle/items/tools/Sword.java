package com.autodidax.demoncycle.items.tools;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.IHasModel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.item.ItemSword;

public class Sword extends ItemSword implements IHasModel{

	public Sword(String name, ToolMaterial material)
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
