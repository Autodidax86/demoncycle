package com.autodidax.demoncycle.world;

import java.util.ArrayList;
import java.util.Random;

import com.autodidax.demoncycle.interfaces.IStructure;
import com.autodidax.demoncycle.util.BiomeHelper;
import com.autodidax.demoncycle.world.base.StructureGenerator;
import com.autodidax.demoncycle.world.base.WorldGenBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import scala.actors.threadpool.Arrays;

public class WorldGenStructures extends WorldGenBase {
	
	private StructureGenerator HERB_COTTAGE = new StructureGenerator("herb_cottage");
	
	public WorldGenStructures() {
		this.registerStructure(DimensionType.OVERWORLD, BlockGrass.class, HERB_COTTAGE, 25, BiomeForest.class, BiomePlains.class);
	}
}
