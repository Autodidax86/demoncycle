package com.autodidax.demoncycle.network.message;

import com.autodidax.demoncycle.tileentity.TileEntitySpinningWheel;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateSpinningWheel implements IMessage{

	 private BlockPos pos;
	 private String state;
	
	public PacketUpdateSpinningWheel() {}
	
	public PacketUpdateSpinningWheel(BlockPos pos, String state) {
		this.pos = pos;
		this.state = state;
	}
	
	public BlockPos getPos() {
		return this.pos;
	}
	
	public String getState() {
		return this.state;
	}
	
	@Override
    public void fromBytes(ByteBuf buffer)
    {
        int x = buffer.readInt();
        int y = buffer.readInt();
        int z = buffer.readInt();
 
        this.pos   = new BlockPos(x, y, z);
        this.state = ByteBufUtils.readUTF8String(buffer);
    }
 
    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(pos.getX());
        buffer.writeInt(pos.getY());
        buffer.writeInt(pos.getZ());
 
        ByteBufUtils.writeUTF8String(buffer, this.state);
    }

    public static class Handler implements IMessageHandler<PacketUpdateSpinningWheel, IMessage>
    {
        @Override
        public IMessage onMessage(PacketUpdateSpinningWheel message, MessageContext ctx)
        {
            World world = Minecraft.getMinecraft().world;

            if (world.getTileEntity(message.getPos()) instanceof TileEntitySpinningWheel)
            {
                TileEntitySpinningWheel spinningWheel = (TileEntitySpinningWheel) world.getTileEntity(message.getPos());
                spinningWheel.switchAnimationClient(message.getState());
            }

            return null;
        }
    }
}
