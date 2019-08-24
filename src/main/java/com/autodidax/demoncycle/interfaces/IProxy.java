package com.autodidax.demoncycle.interfaces;

import net.minecraft.item.Item;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;

public interface IProxy {
	void registerItemRenderer(Item item, int meta, String id);
	void registerBlockRenderer();
	void registerEntityRenders();
	IAnimationStateMachine loadASM(String resoucePath);
}
