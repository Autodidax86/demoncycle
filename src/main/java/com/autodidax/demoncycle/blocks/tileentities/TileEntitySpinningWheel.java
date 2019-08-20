package com.autodidax.demoncycle.blocks.tileentities;

import com.autodidax.demoncycle.blocks.BlockSpinningWheel;
import com.autodidax.demoncycle.blocks.recipes.SpinningWheelRecipes;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.items.ItemBase;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySpinningWheel extends TileEntity implements ITickable {

	private ItemStackHandler spinningWheelItemStacks = new ItemStackHandler(2);
	private String customName;
	private ItemStack spinning = ItemStack.EMPTY;

	private int processTime; 
	private int totalProcessTime = 200;

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
			return (T) this.spinningWheelItemStacks;
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
		this.spinningWheelItemStacks.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.processTime = compound.getInteger("ProcessTime");
		this.totalProcessTime = compound.getInteger("ProcessTimeTotal");
		
		if (compound.hasKey("CustomName", 8))
			this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("ProcessTime", (short) this.processTime);
		compound.setInteger("ProcessTimeTotal", (short) this.totalProcessTime);
		compound.setTag("Inventory", this.spinningWheelItemStacks.serializeNBT());

		if (this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}

	public boolean isWorking() {
		return true; //this "furnace" will always work
	}

	@SideOnly(Side.CLIENT)
	public static boolean isWorking(TileEntitySpinningWheel te) {
		return te.getField(0) > 0;
	}

	public void update()
	{
		boolean flag = this.isWorking();
		boolean flag1 = false;
		
		if(!this.world.isRemote)
		{
			ItemStack input = this.spinningWheelItemStacks.getStackInSlot(0);
			
			if (this.isWorking() || !input.isEmpty())
			{
				if (!this.isWorking() && this.canBeProcessed(input))
				{					
					if(this.isWorking()) {
						flag1 = true;
						
						if(!input.isEmpty()) 
						{
							Item item = input.getItem();
							input.shrink(1);
							
							if (input.isEmpty())
							{
								ItemStack item1 = item.getContainerItem(input);
								this.spinningWheelItemStacks.setStackInSlot(1, item1);
							}
						}
					}
				}
				
				if (this.isWorking() && this.canBeProcessed(input)) {
					++this.processTime;
					
					if(this.processTime == this.totalProcessTime)
					{
						this.processTime = 0;
						this.totalProcessTime = this.getItemProcessTime(input);
						this.processItem(input);
						flag1 = true;
					}
				}
				else
				{
					this.processTime = 0;
				}
			}
			else if (!this.isWorking() && this.processTime > 0)
			{
				this.processTime = MathHelper.clamp(this.processTime - 2, 0, this.totalProcessTime);
			}
			
			if (flag != this.isWorking())
			{
				flag1 = true;
				BlockSpinningWheel.setState(this.isWorking(), this.world, pos);
			}
		}
		
		if (flag1)
		{
			this.markDirty();
		}
	}
	
	public void processItem(ItemStack input)
    {
        if (this.canBeProcessed(input))
        {
            ItemStack resultItem = SpinningWheelRecipes.getInstance().getSpinningResult(input);
            ItemStack output = this.spinningWheelItemStacks.getStackInSlot(1); //output

            if (output.isEmpty())
            {
                this.spinningWheelItemStacks.setStackInSlot(1, resultItem.copy());
            }
            else if (output.getItem() == resultItem.getItem())
            {
            	output.grow(resultItem.getCount());
            }

            input.shrink(1);
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
				ItemStack output = (ItemStack) this.spinningWheelItemStacks.getStackInSlot(1);
				if (output.isEmpty()) return true;
				if (!output.isItemEqual(result)) return false;
					
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}

	public static int getItemProcessTime(ItemStack item) {
		return 200; //we have no fuel but we want to have a process time per item
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
		case 0:
			this.processTime = value;
			break;
		case 1:
			this.totalProcessTime = value;
		}
	}
}
