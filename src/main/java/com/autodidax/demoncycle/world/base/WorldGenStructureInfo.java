package com.autodidax.demoncycle.world.base;

import java.util.ArrayList;

import com.autodidax.demoncycle.interfaces.IStructure;

import net.minecraft.block.Block;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenStructureInfo {
	
	private DimensionType dimensionType;
	private String name;
	private int chance;
	private Class<? extends Block> topBlock;
	private Class<? extends Biome>[] biomes;
	private WorldGenerator structureGenerator;
	
	public WorldGenStructureInfo(DimensionType dimensionType, Class<? extends Block> topBlock, WorldGenerator structureGenerator, int chance, Class<? extends Biome>... biomes) {
		this.dimensionType = dimensionType;
		this.topBlock = topBlock;
		this.structureGenerator = structureGenerator;
		this.chance = chance;
		this.biomes = biomes;
	}
	
	public DimensionType getDimensionType() {
		return this.dimensionType;
	}
	
	public Class<? extends Block> getTopBlock(){
		return this.topBlock;
	}
	
	public WorldGenerator getStructureGenerator() {
		return this.structureGenerator;
	}
	
	public int getChance() {
		return this.chance;
	}
	
	public Class<? extends Biome>[] getBiomes(){
		return this.biomes;
	}
}
