package com.autodidax.demoncycle.tileentities;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.block.BlockSpinningWheel;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.items.ItemBase;
import com.autodidax.demoncycle.recipes.SpinningWheelRecipes;
import com.autodidax.demoncycle.util.Reference;
import com.google.common.collect.ImmutableMap;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySpinningWheel extends TileEntity implements ITickable {

	private ItemStackHandler spinningWheelItemStacks = new ItemStackHandler(2);
	private String customName;
	private ItemStack spinning = ItemStack.EMPTY;
	private int processTime; 
	private int totalProcessTime;
	private final IAnimationStateMachine asm;

	public TileEntitySpinningWheel() {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			asm = ModelLoaderRegistry.loadASM(new ResourceLocation(Reference.MOD_ID, "asms/block/block_spinning_wheel.json"), ImmutableMap.of());
		} else asm = null;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
			return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) this.spinningWheelItemStacks;
		}
			
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		return getCapability(capability, facing) != null;
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
		if(!this.world.isRemote)
		{
			ItemStack input = this.spinningWheelItemStacks.getStackInSlot(0);
			this.totalProcessTime = this.getItemProcessTime(input);
			if (!input.isEmpty())
			{				
				if (this.canBeProcessed(input)) {
					++this.processTime;
					
					if(this.processTime == this.totalProcessTime)
					{
						this.processTime = 0;
						this.totalProcessTime = this.getItemProcessTime(input);
						this.processItem(input);
					}
					else {
						//BlockSpinningWheel.setState(true, this.world, pos);
					}
				}
				else
				{
					this.processTime = 0;
					//BlockSpinningWheel.setState(false, this.world, pos);
				}
			}
			else if (this.processTime > 0)
			{
				this.processTime = MathHelper.clamp(this.processTime - 2, 0, this.totalProcessTime);
			}
			else {
				//BlockSpinningWheel.setState(false, this.world, pos);
			}
		}
		
		this.markDirty();
	}
	
	public void processItem(ItemStack input)
    {
        if (this.canBeProcessed(input))
        {
        	int inputAmount = SpinningWheelRecipes.getInstance().getInputAmount(input);
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

            input.shrink(inputAmount);
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

	public static boolean isItemValid(ItemStack item) {
		return SpinningWheelRecipes.getInstance().getSpinningResult(item) != ItemStack.EMPTY;
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
