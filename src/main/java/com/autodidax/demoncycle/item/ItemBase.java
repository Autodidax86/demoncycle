package com.autodidax.demoncycle.item;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.IHasModel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBase extends Item implements IHasModel
{
	public ItemBase(String name)
	{
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
