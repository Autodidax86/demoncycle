package com.autodidax.demoncycle.init;

import java.util.ArrayList;
import java.util.List;

import com.autodidax.demoncycle.tileentities.TileEntitySpinningWheel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {	
	public static void Register() {		
		GameRegistry.registerTileEntity(TileEntitySpinningWheel.class, new ResourceLocation(Reference.MOD_ID+":"+"spinning_wheel"));
	}
}
