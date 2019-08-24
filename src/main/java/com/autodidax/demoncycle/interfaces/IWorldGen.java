package com.autodidax.demoncycle.interfaces;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public interface IWorldGen {
	IBlockState getStateForWorldGen();
	boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state);
	ArrayList<Biome> getAllowedBiomes();
}
