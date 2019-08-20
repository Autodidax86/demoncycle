package com.autodidax.demoncycle.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockOre extends BlockBase {

	private Item item;
	/* harvestLevel = 
     *     Wood:    0
     *     Stone:   1
     *     Iron:    2
     *     Diamond: 3
     *     Gold:    0
     */
	
	public BlockOre(String name, float hardness, float resistance, int harvestLevel) {
		super(name, Material.ROCK);

		setHardness(hardness);
		setResistance(resistance);
		setResistance(15.0f);
		setHarvestLevel("pickaxe", harvestLevel);
		//setLightLevel(0f);
		//setLightOpacity(0);
		//setBlockUnbreakable();
	}
	
	public BlockOre(String name, float hardness, float resistance, int harvestLevel, Item droppedItem) {
		this(name, hardness, resistance, harvestLevel);
		this.item = droppedItem;
	}

	@Override
	public BlockOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.item == null ? Item.getItemFromBlock(this) : this.item;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
}
