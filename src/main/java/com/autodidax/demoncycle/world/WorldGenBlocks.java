package com.autodidax.demoncycle.world;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.core.net.Facility;

import com.autodidax.demoncycle.block.BlockCrop;
import com.autodidax.demoncycle.block.BlockOre;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.interfaces.IWorldGen;
import com.autodidax.demoncycle.world.base.WorldGenBase;

import net.minecraft.block.BlockBeetroot;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlowerPot.EnumFlowerType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeForestMutated;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.MetadataCollection;

public class WorldGenBlocks extends WorldGenBase
{	
	public WorldGenBlocks() {
		this.registerBlock(DimensionType.OVERWORLD, ModBlocks.ORE_COPPER, 1, 63, 4, 30);
		this.registerBlock(DimensionType.OVERWORLD, ModBlocks.CROP_BLACKSTEM, 64, 100, 1, 1);
		this.registerBlock(DimensionType.OVERWORLD, ModBlocks.CROP_FLAX, 64, 100, 1, 1);
	}	
}
