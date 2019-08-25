package com.autodidax.demoncycle;

import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModEntities;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.init.ModRecipes;
import com.autodidax.demoncycle.init.ModTileEntities;
import com.autodidax.demoncycle.interfaces.IProxy;
import com.autodidax.demoncycle.network.message.PacketRequestUpdateSpinningWheel;
import com.autodidax.demoncycle.network.message.PacketUpdateSpinningWheel;
import com.autodidax.demoncycle.util.Reference;
import com.autodidax.demoncycle.util.handler.GuiHandler;
import com.autodidax.demoncycle.util.handler.RenderHandler;
import com.autodidax.demoncycle.world.WorldGenBlocks;
import com.autodidax.demoncycle.world.WorldGenStructures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	@Instance
	public static Main Instance;
	
	public static SimpleNetworkWrapper network;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		network.registerMessage(new PacketUpdateSpinningWheel.Handler(), PacketUpdateSpinningWheel.class, 0, Side.CLIENT);
		network.registerMessage(new PacketRequestUpdateSpinningWheel.Handler(), PacketRequestUpdateSpinningWheel.class, 1, Side.SERVER);
		
		GameRegistry.registerWorldGenerator(new WorldGenBlocks(), 3);
		GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);
		
		ModEntities.Init();
		
		Main.proxy.registerEntityRenders();
		Main.proxy.registerBlockRenderer();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModBlocks.init();
		ModItems.Init();
		ModRecipes.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.Instance, new GuiHandler());
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		
	}
}
