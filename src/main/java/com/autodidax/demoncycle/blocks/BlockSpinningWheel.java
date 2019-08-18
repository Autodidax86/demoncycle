package com.autodidax.demoncycle.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSpinningWheel extends BlockBase {

	public static final AxisAlignedBB SPINNING_WHEEL_AABB = new AxisAlignedBB(0.1875D, 0, 0.1875D, 0.8125D, 0.625D, 0.8125D);
	
	public BlockSpinningWheel(String name) {
		super(name, Material.WOOD);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SPINNING_WHEEL_AABB;
	}
}
