package com.autodidax.demoncycle;

import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModEntities;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.init.ModRecipes;
import com.autodidax.demoncycle.proxy.CommonProxy;
import com.autodidax.demoncycle.util.Reference;
import com.autodidax.demoncycle.util.handlers.RenderHandler;
import com.autodidax.demoncycle.world.ModWorldGen;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	@Instance
	public static Main Instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		ModEntities.Init();
		RenderHandler.registerEntityRenders();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModBlocks.init();
		ModItems.Init();
		ModRecipes.init();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		
	}
}
