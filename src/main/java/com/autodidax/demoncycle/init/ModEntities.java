package com.autodidax.demoncycle.init;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.entity.EntitySandDemon;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void Init() {
		registerEntity("sanddemon", EntitySandDemon.class, Reference.EntitySandDemon, 50, 12696440, 16767021);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) 
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.Instance, 
				range, 1, true, color1, color2);
	}
}
