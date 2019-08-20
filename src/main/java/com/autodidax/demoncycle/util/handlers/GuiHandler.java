package com.autodidax.demoncycle.util.handlers;

import com.autodidax.demoncycle.container.ContainerSpinningWheel;
import com.autodidax.demoncycle.gui.GuiSpinningWheel;
import com.autodidax.demoncycle.tileentities.TileEntitySpinningWheel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_SPINNING_WHEEL) return new ContainerSpinningWheel(player.inventory, (TileEntitySpinningWheel)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_SPINNING_WHEEL) return new GuiSpinningWheel(player.inventory, (TileEntitySpinningWheel)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
