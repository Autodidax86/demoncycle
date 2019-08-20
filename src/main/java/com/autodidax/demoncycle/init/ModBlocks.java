package com.autodidax.demoncycle.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.autodidax.demoncycle.block.BlockBase;
import com.autodidax.demoncycle.block.BlockCopper;
import com.autodidax.demoncycle.block.BlockCrop;
import com.autodidax.demoncycle.block.BlockCropBlackstem;
import com.autodidax.demoncycle.block.BlockCropFlax;
import com.autodidax.demoncycle.block.BlockOre;
import com.autodidax.demoncycle.block.BlockSpinningWheel;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeForestMutated;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final BlockCrop CROP_BLACKSTEM = new BlockCropBlackstem(); 
	public static final BlockCrop CROP_FLAX = new BlockCropFlax();
	public static final BlockCopper BLOCK_COPPER = new BlockCopper("block_copper");
	public static final BlockOre ORE_COPPER = new BlockOre("ore_copper", 3f, 5f, 1);
	public static final BlockSpinningWheel BLOCK_SPINNING_WHEEL = new BlockSpinningWheel("block_spinning_wheel");
	//public static final BlockChair BLOCK_CHAIR = new BlockChair("block_chair"); //Buggy
	
	public static void init() {
		OreDictionary.registerOre("oreCopper", ORE_COPPER);
		OreDictionary.registerOre("blockCopper", BLOCK_COPPER);
	}
}