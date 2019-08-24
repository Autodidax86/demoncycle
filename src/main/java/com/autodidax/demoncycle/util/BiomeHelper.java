package com.autodidax.demoncycle.util;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeHelper {
	public static Biome[] getBiomes(final BiomeDictionary.Type type) {
		return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
	}
}
