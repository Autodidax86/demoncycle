package com.autodidax.demoncycle.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.item.ItemStack;;

public class SpinningWheelRecipes 
{	
	private static final SpinningWheelRecipes INSTANCE = new SpinningWheelRecipes();
	private final Map<ItemStack, ItemStack> spinningList = Maps.<ItemStack, ItemStack>newHashMap();
	//private final Table<ItemStack, ItemStack, ItemStack> spinningList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SpinningWheelRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private SpinningWheelRecipes() 
	{
	}

	
	public void addSpinningWheelRecipe(ItemStack input1, ItemStack result, float experience) 
	{
		if(getSpinningResult(input1) != ItemStack.EMPTY) return;
		this.spinningList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getSpinningResult(ItemStack input) 
	{
		for (Entry<ItemStack, ItemStack> entry : this.spinningList.entrySet()) 
		{
			if(this.compareItemStacks(input, (ItemStack)entry.getKey())) 
			{
				return (ItemStack)entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getSpinningList() 
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
