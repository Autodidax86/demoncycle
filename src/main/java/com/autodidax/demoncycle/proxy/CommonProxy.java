package com.autodidax.demoncycle.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;

public class CommonProxy 
{
	public void registerItemRenderer(Item item, int meta, String id){}
	public void registerBlockRenderer()  {}
	public void registerEntityRenders() {}
	public IAnimationStateMachine loadASM(String resoucePath) {return null;}
}
