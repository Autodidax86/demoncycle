package com.autodidax.demoncycle.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.item.ItemStack;;

public class SpinningWheelRecipes 
{	
	private static final SpinningWheelRecipes INSTANCE = new SpinningWheelRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> spinningList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SpinningWheelRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private SpinningWheelRecipes() 
	{
	}

	
	public void addSpinningWheelRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) 
	{
		if(getSpinningResult(input1, input2) != ItemStack.EMPTY) return;
		this.spinningList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getSpinningResult(ItemStack input1, ItemStack input2) 
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.spinningList.columnMap().entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) 
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) 
					{
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getSpinningList() 
	{
		return this.spinningList;
	}
	
	public float getSpinningExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
