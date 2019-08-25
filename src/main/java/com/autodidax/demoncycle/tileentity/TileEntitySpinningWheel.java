package com.autodidax.demoncycle.tileentity;

import com.autodidax.demoncycle.Main;
import com.autodidax.demoncycle.block.BlockSpinningWheel;
import com.autodidax.demoncycle.init.ModBlocks;
import com.autodidax.demoncycle.init.ModItems;
import com.autodidax.demoncycle.item.ItemBase;
import com.autodidax.demoncycle.network.message.PacketUpdateSpinningWheel;
import com.autodidax.demoncycle.recipe.SpinningWheelRecipes;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySpinningWheel extends TileEntity implements ITickable, ICapabilityProvider {

	public ItemStackHandler inventory;
	private String customName;
	private int processTime; 
	private int totalProcessTime;
	private long lastChangeTime;
	private String currentState;
	private final IAnimationStateMachine asm;

	public TileEntitySpinningWheel() {
		asm = Main.proxy.loadASM("asms/block/block_spinning_wheel.json");
		
		if(asm != null)
		{
			this.currentState = asm.currentState();
		}
		
		this.inventory = new ItemStackHandler(2);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
			return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) this.inventory;
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
	
	public String getCurrentState() {
		return this.currentState;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName)
				: new TextComponentTranslation("container.spinning_wheel");
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.processTime = compound.getInteger("ProcessTime");
		this.totalProcessTime = compound.getInteger("ProcessTimeTotal");
		this.lastChangeTime = compound.getLong("LastChangeTime");
		this.currentState = compound.getString("CurrentState");
		
		if (compound.hasKey("CustomName", 8))
			this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("ProcessTime", (short) this.processTime);
		compound.setInteger("ProcessTimeTotal", (short) this.totalProcessTime);
		compound.setLong("LastChangeTime", this.lastChangeTime);
		compound.setTag("Inventory", this.inventory.serializeNBT());
		compound.setString("CurrentState", this.currentState);

		if (this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}

	//Kind of callback?
	public void switchAnimationClient(String newState) {	
		String currentState = this.asm.currentState();
		
		if(!currentState.equalsIgnoreCase(newState)) {
			this.asm.transition(newState);
		}
	}
	
	public void SendClientUpdate(String newState) {
		this.currentState = newState;
		Main.network.sendToAllAround(new PacketUpdateSpinningWheel(pos, newState), 
				new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
	}
	
	public void update()
	{
		if(!this.world.isRemote)
		{
			ItemStack input = this.inventory.getStackInSlot(0);
			this.totalProcessTime = this.getItemProcessTime(input);
			if (!input.isEmpty())
			{				
				if (this.canBeProcessed(input)) {
					++this.processTime;
					
					if(this.processTime == this.totalProcessTime)
					{
						this.processTime = 0;
						this.SendClientUpdate("default");
						
						this.totalProcessTime = this.getItemProcessTime(input);
						this.processItem(input);
					}
					else {
						this.SendClientUpdate("moving");
					}
				}
				else
				{
					this.processTime = 0;
					this.SendClientUpdate("default");
				}
			}
			else if (this.processTime > 0)
			{
				this.processTime = 0;
			}
			else {
				this.SendClientUpdate("default");
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
            ItemStack output = this.inventory.getStackInSlot(1); //output

            if (output.isEmpty())
            {
                this.inventory.setStackInSlot(1, resultItem.copy());
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
				ItemStack output = (ItemStack) this.inventory.getStackInSlot(1);
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
