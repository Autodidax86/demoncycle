package com.autodidax.demoncycle.block;

import java.util.ArrayList;
import java.util.Arrays;

import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;

public class BlockCropBlackstem extends BlockCrop {
	
	public static final PropertyInteger BLOCK_AGE = PropertyInteger.create("age", 0, 3);
	
	public BlockCropBlackstem() {
		super("crop_blackstem", 3, new ArrayList<Biome>(Arrays.asList(Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.BIRCH_FOREST_HILLS, Biomes.BIRCH_FOREST, Biomes.ROOFED_FOREST)));
	}

	@Override
	protected Item getSeed() {
		return ModItems.SEED_BLACKSTEM;
	}

	@Override
	protected Item getCrop() {
		return ModItems.BLACKSTEM;
	}
	
	@Override
	public int getMaxAge() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	@Override
	protected PropertyInteger getAgeProperty()
    {
        return BLOCK_AGE;
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {BLOCK_AGE});
	}
	
	@Override
	public BlockCropBlackstem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}