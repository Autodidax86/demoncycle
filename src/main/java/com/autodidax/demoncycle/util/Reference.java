package com.autodidax.demoncycle.util;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Reference {

	public static final CreativeTabs DemonCycleTab = new DemonCycleTab();
	public static final String MOD_ID = "demoncycle";
	public static final String NAME = "Demoncycle Mod";
	public static final String VERSION = "1.0";
	public static final String ACCEPTED_VERSIONS = "(1.12.2)";
	public static final String CLIENT_PROXY_CLASS = "com.autodidax.demoncycle.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.autodidax.demoncycle.proxy.ServerProxy";
	
	public static final int EntitySandDemon = 120;
	public static final int GUI_SPINNING_WHEEL = 0;
}