package com.autodidax.demoncycle.util.handlers;

import com.autodidax.demoncycle.entity.EntitySandDemon;
import com.autodidax.demoncycle.entity.render.RenderSandDemon;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySandDemon.class, new IRenderFactory<EntitySandDemon>() 
		{
			@Override
			public Render<? super EntitySandDemon> createRenderFor(RenderManager manager) {
				// TODO Auto-generated method stub
				return new RenderSandDemon(manager);
			}
		});
	}
}
