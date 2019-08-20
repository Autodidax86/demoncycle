package com.autodidax.demoncycle.block;

import java.util.ArrayList;

import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.util.IWorldGen;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCrop extends BlockCrops implements IWorldGen {

private PropertyInteger block_age;
private int max_age;
private Item seed;
private Item crop;
private ArrayList<Biome> allowedBiomes;
	
	public BlockCrop(String name, int maxAge, ArrayList<Biome> allowedBiomes) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.DemonCycleTab);
		ModBlocks.BLOCKS.add(this);
		
		this.block_age = PropertyInteger.create("age", 0, maxAge);
		this.max_age = maxAge;
		this.allowedBiomes = allowedBiomes;
	}
	
	public ArrayList<Biome> getAllowedBiomes() {
		if (this.allowedBiomes == null) {
			this.allowedBiomes = new ArrayList<Biome>();
		}
		
		return this.allowedBiomes;
	}
	
	@Override
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
				EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			// TODO Auto-generated method stub
			return super.onBlockActivated(worldIn, pos, this.withAge(getMaxAge()), playerIn, hand, facing, hitX, hitY, hitZ);
		}
	
	@Override
	public int getMaxAge() {
		// TODO Auto-generated method stub
		return this.max_age;
	}
	
	@Override
	protected PropertyInteger getAgeProperty()
    {
        return this.block_age;
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {this.block_age});
	}
	
	@Override
	public BlockCrop setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public IBlockState getStateForWorldGen() {
		// TODO Auto-generated method stub
		return this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(this.max_age));
	}
}
