package com.autodidax.demoncycle.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCopper extends BlockBase 
{
	public BlockCopper(String name) 
	{
		super(name, Material.IRON);
		setSoundType(SoundType.METAL);
		setHardness(3.0f);
		setResistance(15.0f);
		setHarvestLevel("pickaxe", 1);
	}
}
