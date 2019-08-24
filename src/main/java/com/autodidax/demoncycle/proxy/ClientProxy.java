package com.autodidax.demoncycle.proxy;

import com.autodidax.demoncycle.tileentity.TileEntitySpinningWheel;
import com.autodidax.demoncycle.util.Reference;
import com.autodidax.demoncycle.util.handler.RenderHandler;
import com.google.common.collect.ImmutableMap;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
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
	
	@Override
	public IAnimationStateMachine loadASM(String resourcePath) {
		return ModelLoaderRegistry.loadASM(new ResourceLocation(Reference.MOD_ID, resourcePath), ImmutableMap.of());
	}
	
	@Override
	public void registerEntityRenders() {
		RenderHandler.registerEntityRenders();
	}
}
