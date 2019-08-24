package com.autodidax.demoncycle.world.base;

import java.util.ArrayList;
import java.util.Random;

import com.autodidax.demoncycle.block.BlockCrop;
import com.autodidax.demoncycle.block.BlockOre;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.interfaces.IWorldGen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenBase implements IWorldGenerator {
	
	private ArrayList<WorldGenBlockInfo> blocks = new ArrayList<>();
	
	public void registerBlock(DimensionType dimension, Block block, int minY, int maxY, int veinSize, int chances) {
		this.blocks.add(new WorldGenBlockInfo(dimension, block, minY, maxY, veinSize, chances));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		for (WorldGenBlockInfo info : blocks) {
			if (info.getDimensionType() == world.provider.getDimensionType()) {
				
				if(info.getBlock() instanceof BlockOre) {
					generateOre(info.getBlock().getDefaultState(), world, random, chunkX * 16, chunkZ * 16, info.getMinY(), info.getMaxY(), info.getVeinSize() + random.nextInt(3), info.getChances());
				}
				else if (info.getBlock() instanceof BlockCrop) {
					generateCrop((BlockCrop)info.getBlock(), world, random, chunkX * 16, chunkZ * 16, info.getMinY(), info.getMaxY(), info.getVeinSize(), info.getChances());
				}
			}
		}
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
