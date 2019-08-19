package com.autodidax.demoncycle.util.handlers;

import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.init.ModTileEntities;
import com.autodidax.demoncycle.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) 
	{		
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) 
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		ModTileEntities.Register();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) 
	{
		for(Item item : ModItems.ITEMS)
		{
			if(item instanceof IHasModel) 
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if(block instanceof IHasModel) 
			{
				((IHasModel)block).registerModels();
			}
		}
	}
}
