package com.autodidax.demoncycle.proxy;

import com.autodidax.demoncycle.tileentity.TileEntitySpinningWheel;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	@Override
	public void registerBlockRenderer()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpinningWheel.class, new AnimationTESR<TileEntitySpinningWheel>() {
			@Override
			public void handleEvents(TileEntitySpinningWheel te, float time, Iterable<Event> pastEvents) {
				// TODO Auto-generated method stub
				super.handleEvents(te, time, pastEvents);
			}
		});
	}
}
