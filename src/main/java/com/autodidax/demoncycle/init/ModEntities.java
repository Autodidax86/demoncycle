package com.autodidax.demoncycle.init;

import java.util.List;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.entity.EntitySandDemon;
import com.autodidax.demoncycle.util.BiomeHelper;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModEntities 
{
	public static void Init() {
		registerEntity("sanddemon", EntitySandDemon.class, Reference.EntitySandDemon, EntityCreeper.class, EnumCreatureType.MONSTER, 50, 12696440, 16767021, 
			SpawnPlacementType.ON_GROUND, BiomeHelper.getBiomes(BiomeDictionary.Type.SANDY), 100, 10, 20);
	}
	
	private static void registerEntity(String name, Class<? extends EntityLiving> entity, int id, Class<? extends EntityLiving> classToCopy, EnumCreatureType creatureType,
			int range, int color1, int color2, SpawnPlacementType spawnType, Biome[] biomes, int weight, int min, int max) 
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.Instance, 
				range, 1, true, color1, color2);		
		EntityRegistry.addSpawn(entity, weight, min, max, creatureType, biomes);
	}
}
