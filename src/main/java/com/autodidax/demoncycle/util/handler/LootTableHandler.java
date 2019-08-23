package com.autodidax.demoncycle.util.handler;

import com.autodidax.demoncycle.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler 
{
	public static final ResourceLocation SandDemon = LootTableList.register(new ResourceLocation(Reference.MOD_ID + ":sand_demon"));
}
