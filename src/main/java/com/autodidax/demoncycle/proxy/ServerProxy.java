package com.autodidax.demoncycle.proxy;

import com.autodidax.demoncycle.interfaces.IProxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;

public class ServerProxy implements IProxy
{
	public void registerItemRenderer(Item item, int meta, String id){}
	public void registerBlockRenderer()  {}
	public void registerEntityRenders() {}
	public IAnimationStateMachine loadASM(String resoucePath) {return null;}
}
