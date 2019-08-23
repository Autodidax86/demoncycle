package com.autodidax.demoncycle.block;

import java.util.Random;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.container.BlockContainerBase;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.tileentities.TileEntitySpinningWheel;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;

public class BlockSpinningWheel extends BlockContainerBase implements ITileEntityProvider {

	public static final AxisAlignedBB SPINNING_WHEEL_NORTH_AABB = new AxisAlignedBB(1D, 0, 0.75D, 0.125D, 1.25D, 0.25D);
	public static final AxisAlignedBB SPINNING_WHEEL_WEST_AABB = new AxisAlignedBB(0.25D, 0, 0, 0.75D, 1.25D, 0.875D);
	public static final AxisAlignedBB SPINNING_WHEEL_SOUTH_AABB = new AxisAlignedBB(0, 0, 0.25D, 0.875D, 1.25D, 0.75D);
	public static final AxisAlignedBB SPINNING_WHEEL_EAST_AABB = new AxisAlignedBB(0.75D, 0, 0.125D, 0.25D, 1.25D, 1D);
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockSpinningWheel(String name) {
		super(name, Material.WOOD);
		setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
		switch ((EnumFacing)state.getValue(FACING))
        {
            case NORTH:
                return SPINNING_WHEEL_NORTH_AABB;
            case SOUTH:
                return SPINNING_WHEEL_SOUTH_AABB;
            case WEST:
                return SPINNING_WHEEL_WEST_AABB;
            case EAST:
            	return SPINNING_WHEEL_EAST_AABB;
            default:
                return SPINNING_WHEEL_SOUTH_AABB;
        }

	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.BLOCK_SPINNING_WHEEL);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ModBlocks.BLOCK_SPINNING_WHEEL);	
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if(tileentity instanceof TileEntitySpinningWheel) {
				playerIn.openGui(Main.Instance, Reference.GUI_SPINNING_WHEEL, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (!worldIn.isRemote) 
        {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntitySpinningWheel();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		//return new BlockStateContainer(this, new IProperty[] {FACING});
		return new ExtendedBlockState(this, new IProperty<?>[] {Properties.StaticProperty, FACING}, new IUnlistedProperty<?>[] {Properties.AnimationProperty});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		// TODO Auto-generated method stub
		return state.withProperty(Properties.StaticProperty, true);
	}
}
