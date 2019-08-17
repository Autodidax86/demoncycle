package com.autodidax.demoncycle.items.tools;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.IHasModel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;

public class Axe extends ItemAxe implements IHasModel{

	public Axe(String name, ToolMaterial material, float damage, float speed)
	{
		super(material, damage, speed);
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
