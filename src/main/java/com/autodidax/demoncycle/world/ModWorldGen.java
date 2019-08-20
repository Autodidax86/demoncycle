package com.autodidax.demoncycle.world;

import java.io.Console;
import java.util.Random;

import org.apache.logging.log4j.core.net.Facility;

import com.autodidax.demoncycle.block.BlockCrop;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.util.IWorldGen;

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

public class ModWorldGen implements IWorldGenerator 
{	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		if (world.provider instanceof WorldProviderSurface) { // the overworld
			generateSurface(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
			
			
		}
	}
	
private void generateSurface(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
	generateOre(ModBlocks.ORE_COPPER.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 1, 63, 4 + random.nextInt(3), 30);
	generateCrop(ModBlocks.CROP_BLACKSTEM, world, random, chunkX * 16, chunkZ * 16, 64, 100, 1, 1);
	generateCrop(ModBlocks.CROP_FLAX, world, random, chunkX * 16, chunkZ * 16, 64, 100, 1, 1);
	}

private void generateCrop(IWorldGen crop, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int size, int chances) {
	int deltaY = maxY - minY;	
	for(int rarity = 0; rarity < chances; rarity++) {
		int x = chunkX + random.nextInt(8);
		int y = minY + random.nextInt(deltaY);
		int z = chunkZ + random.nextInt(8);
		if(matchesBiome(crop, world.getBiome(new BlockPos(x, 0, z))))
		{
			generateBush(crop, world, random, new BlockPos(x,y,z));
		}
	}
}

private void generateBush(IWorldGen crop, World worldIn, Random rand, BlockPos position) {
	for (int i = 0; i < 64; ++i)
    {
        BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
        IBlockState blockState = crop.getStateForWorldGen();

        if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < worldIn.getHeight() - 1) && crop.canBlockStay(worldIn, blockpos, blockState))
        {
            worldIn.setBlockState(blockpos, blockState, 2);
        }
    }
}

private void generateOre(IBlockState ore, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int veinSize, int chances) {
	int deltaY = maxY - minY;
	
	for(int rarity = 0; rarity < chances; rarity++) {
		int x = chunkX + random.nextInt(8);
		int y = minY + random.nextInt(deltaY);
		int z = chunkZ + random.nextInt(8);
		new WorldGenMinable(ore, veinSize).generate(world, random, new BlockPos(x, y, z));			
	}
}

private Boolean matchesBiome(IWorldGen crop, Biome currentBiome) {
	if(crop.getAllowedBiomes().isEmpty()) {
		return true;
	}
	
	int currentBiomeId = Biome.getIdForBiome(currentBiome);
	
	for (Biome biome : crop.getAllowedBiomes()) {
		int allowedBiomeId = Biome.getIdForBiome(biome);
		if(allowedBiomeId == currentBiomeId)
		{
			return true;
		}
	}
	
	return false;
}

}
