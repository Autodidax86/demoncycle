package com.autodidax.demoncycle.world.base;

import com.autodidax.demoncycle.block.BlockOre;

import net.minecraft.block.Block;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

public class WorldGenBlockInfo<T extends Block>{
	private T block;
	private int minY, maxY, veinSize, chances;
	private DimensionType dimension;
	
	public WorldGenBlockInfo(DimensionType dimension, T block, int minY, int maxY, int veinSize, int chances) {
		this.block = block;
		this.minY = minY;
		this.maxY = maxY;
		this.veinSize = veinSize;
		this.chances = chances;
		this.dimension = dimension;
	}
	
	public T getBlock() {
		return this.block;	
	}
	
	public int getMinY() {
		return this.minY;
	}
	
	public int getMaxY() {
		return this.maxY;
	}
	
	public int getVeinSize() {
		return this.veinSize;
	}
	
	public int getChances() {
		return this.chances;
	}
	
	public DimensionType getDimensionType() {
		return this.dimension;
	}
}