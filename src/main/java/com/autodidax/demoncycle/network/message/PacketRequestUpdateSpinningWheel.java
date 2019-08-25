package com.autodidax.demoncycle.network.message;

import com.autodidax.demoncycle.tileentity.TileEntitySpinningWheel;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestUpdateSpinningWheel implements IMessage{

	private BlockPos pos;
	private int dimension;
	private String state;
	
	public PacketRequestUpdateSpinningWheel() {}
	
	public PacketRequestUpdateSpinningWheel(BlockPos pos, int dimension, String state) {
		this.pos = pos;
		this.dimension = dimension;
		this.state = state;
	}
	
	public BlockPos getPos() {
		return this.pos;
	}
	
	public int getDimension() {
		return this.dimension;
	}
	
	public String getState() {
		return this.state;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(dimension);
		ByteBufUtils.writeUTF8String(buf, this.state);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		dimension = buf.readInt();
		this.state = ByteBufUtils.readUTF8String(buf);
	}

    public static class Handler implements IMessageHandler<PacketRequestUpdateSpinningWheel, IMessage>
    {
    	@Override
		public PacketUpdateSpinningWheel onMessage(PacketRequestUpdateSpinningWheel message, MessageContext ctx) {
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimension);
			TileEntitySpinningWheel te = (TileEntitySpinningWheel)world.getTileEntity(message.pos);
			if (te != null) {
				return new PacketUpdateSpinningWheel(te.getPos(), te.getCurrentState());
			} else {
				return null;
			}
		}
    }
}
