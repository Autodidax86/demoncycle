package com.autodidax.demoncycle.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.autodidax.demoncycle.blocks.BlockBase;
import com.autodidax.demoncycle.blocks.BlockCopper;
import com.autodidax.demoncycle.blocks.BlockCrop;
import com.autodidax.demoncycle.blocks.BlockCropBlackstem;
import com.autodidax.demoncycle.blocks.BlockCropFlax;
import com.autodidax.demoncycle.blocks.BlockOre;

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
	
	public static void init() {
		OreDictionary.registerOre("oreCopper", ORE_COPPER);
		OreDictionary.registerOre("blockCopper", BLOCK_COPPER);
	}
}