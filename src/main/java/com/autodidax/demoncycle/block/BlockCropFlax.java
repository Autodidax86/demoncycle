package com.autodidax.demoncycle.block;

import java.util.ArrayList;
import java.util.Arrays;

import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.interfaces.IWorldGen;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;

public class BlockCropFlax extends BlockCrop {
	
	public static final PropertyInteger BLOCK_AGE = PropertyInteger.create("age", 0, 7);
	
	public BlockCropFlax() {
		super("crop_flax", 7, new ArrayList<Biome>(Arrays.asList(Biomes.PLAINS, Biomes.SWAMPLAND)));
	}

	@Override
	protected Item getSeed() {
		return ModItems.SEED_FLAX;
	}

	@Override
	protected Item getCrop() {
		return ModItems.FLAX;
	}
	
	@Override
	public int getMaxAge() {
		// TODO Auto-generated method stub
		return 7;
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
	public BlockCropFlax setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}