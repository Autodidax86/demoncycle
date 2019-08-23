package com.autodidax.demoncycle.init;

import com.autodidax.demoncycle.recipe.SpinningWheelRecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init() {
		GameRegistry.addSmelting(ModBlocks.ORE_COPPER, new ItemStack(ModItems.INGOT_COPPER, 1), 0.7f);
		
		//SpinningWheel
		SpinningWheelRecipes.getInstance().addSpinningWheelRecipe(new ItemStack(ModItems.FLAX_FIBRE, 8), new ItemStack(ModItems.FLAX_WOVEN,1), 0.5f);
	}	
}
