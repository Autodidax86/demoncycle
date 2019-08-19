package com.autodidax.demoncycle.blocks.tileentities;

import com.autodidax.demoncycle.blocks.BlockSpinningWheel;
import com.autodidax.demoncycle.blocks.recipes.SpinningWheelRecipes;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.items.ItemBase;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySpinningWheel extends TileEntity implements ITickable {

	private ItemStackHandler handler = new ItemStackHandler(2);
	private String customName;
	private ItemStack spinning = ItemStack.EMPTY;

	private int processTime; //cookTime
	private int totalProcessTime = 200; //totalCookTime

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		else
			return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}

	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName)
				: new TextComponentTranslation("container.spinning_wheel");
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.processTime = compound.getInteger("ProcessTime");
		this.totalProcessTime = compound.getInteger("ProcessTimeTotal");
		//this.totalProcessTime = getItemProcessTime((ItemStack) this.handler.getStackInSlot(1));

		if (compound.hasKey("CustomName", 8))
			this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
//		compound.setInteger("BurnTime", (short) this.burnTime);
		compound.setInteger("ProcessTime", (short) this.processTime);
		compound.setInteger("ProcessTimeTotal", (short) this.totalProcessTime);
		compound.setTag("Inventory", this.handler.serializeNBT());

		if (this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}

	public boolean isWorking() {
		return this.processTime > 0; //this "furnace" will always work
	}

	@SideOnly(Side.CLIENT)
	public static boolean isWorking(TileEntitySpinningWheel te) {
		return te.getField(0) > 0;
	}

	public void update() 
	{
		if (this.isWorking()) 
		{
			this.processTime++;
			BlockSpinningWheel.setState(true, world, pos);
		}

		ItemStack input = handler.getStackInSlot(0);
		
		if (this.isWorking() && this.canBeProcessed(input))
		{ 
			this.processTime++;
			if (this.processTime >= this.totalProcessTime) //finished
			{
				if(handler.getStackInSlot(1).getCount() > 0) 
				{
					handler.getStackInSlot(1).grow(1);
				}
				else 
				{
					handler.insertItem(1, spinning, false);
				}
				
				spinning = ItemStack.EMPTY;
				this.processTime = 0;
				return;
			}
		}
		else if(this.canBeProcessed(input)) 
		{
			ItemStack output = SpinningWheelRecipes.getInstance().getSpinningResult(input);
			if(!output.isEmpty()) {
				spinning = output;
				this.processTime++;
				input.shrink(1);
				handler.setStackInSlot(0, input);
			}
		}
		else {
			this.processTime = 0;
		}
	}

	private boolean canBeProcessed(ItemStack item) {
		if (item.isEmpty())
		{
			return false;
		}
		else {
			ItemStack result = SpinningWheelRecipes.getInstance().getSpinningResult(item);
			
			if (result.isEmpty()) {
				return false;
			}
				
			else {
				ItemStack output = (ItemStack) this.handler.getStackInSlot(1);
				if (output.isEmpty()) return true;
				if (!output.isItemEqual(result)) return false;
					
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}

	public static int getItemProcessTime(ItemStack fuel) {
		return 200; //we have no fuel but we want to have a process time
		
		// if(fuel.isEmpty()) return 0;
		// else
		// {
		// Item item = fuel.getItem();
		//
		// if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
		// {
		// Block block = Block.getBlockFromItem(item);
		//
		// if (block == Blocks.WOODEN_SLAB) return 150;
		// if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
		// if (block == Blocks.COAL_BLOCK) return 16000;
		// }
		//
		// if (item instanceof ItemTool &&
		// "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
		// if (item instanceof ItemSword &&
		// "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
		// if (item instanceof ItemHoe &&
		// "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
		// if (item == Items.STICK) return 100;
		// if (item == Items.COAL) return 1600;
		// if (item == Items.LAVA_BUCKET) return 20000;
		// if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
		// if (item == Items.BLAZE_ROD) return 2400;
		//
		// return GameRegistry.getFuelValue(fuel);
		// }
	}

	public static boolean isItemValid(ItemStack fuel) {
		return SpinningWheelRecipes.getInstance().getSpinningResult(fuel) != ItemStack.EMPTY;
	}

	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public int getField(int id) {
		switch (id) {
//		case 0:
//			return this.burnTime;
//		case 0:
//			return this.currentProcessTime;
		case 0:
			return this.processTime;
		case 1:
			return this.totalProcessTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) {
		switch (id) {
//		case 0:
//			this.burnTime = value;
//			break;
//		case 0:
//			this.currentProcessTime = value;
//			break;
		case 0:
			this.processTime = value;
			break;
		case 1:
			this.totalProcessTime = value;
		}
	}
}
