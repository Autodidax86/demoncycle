package com.autodidax.demoncycle.items.food;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.IHasModel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel 
{
	public FoodBase(String name, int amount, float saturation, boolean isAnimalFood) 
	{
		super(amount, saturation, isAnimalFood);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.DemonCycleTab);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		// TODO Auto-generated method stub
		Main.proxy.registerItemRenderer(this, 0, "Inventory");
	}

}
