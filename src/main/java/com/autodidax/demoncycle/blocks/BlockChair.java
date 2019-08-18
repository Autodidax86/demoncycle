package com.autodidax.demoncycle.blocks;

import com.autodidax.demoncycle.util.SeatUtil;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChair extends BlockBase {

	public static final AxisAlignedBB CHAIR_AABB = new AxisAlignedBB(0.1875D, 0, 0.1875D, 0.8125D, 0.625D, 0.8125D);
	
	public BlockChair(String name) {
		super(name, Material.WOOD);
		this.setHardness(0.5F);
        this.setSoundType(SoundType.WOOD);
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
		return CHAIR_AABB;
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		System.out.println("activate seat");
        return !playerIn.isSneaking()
        		&& !SeatUtil.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ())
        		&& SeatUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 9 * 0.0625);
    }
}
