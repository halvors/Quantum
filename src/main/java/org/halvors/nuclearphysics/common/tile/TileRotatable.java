package org.halvors.nuclearphysics.common.tile;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import org.halvors.nuclearphysics.common.NuclearPhysics;
import org.halvors.nuclearphysics.common.network.packet.PacketTileEntity;

import java.util.Arrays;
import java.util.List;

public class TileRotatable extends TileBase implements ITileNetwork, ITileRotatable {
    private static final String NBT_FACING = "facing";

    protected EnumFacing facing = EnumFacing.NORTH;

    public TileRotatable() {

    }

    @Override
    public void readFromNBT(final NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (tag.hasKey(NBT_FACING)) {
            facing = EnumFacing.getFront(tag.getInteger(NBT_FACING));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(final NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (facing != null) {
            tag.setInteger(NBT_FACING, facing.ordinal());
        }

        return tag;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void handlePacketData(final ByteBuf dataStream) {
        if (world.isRemote) {
            facing = EnumFacing.getFront(dataStream.readInt());
        }
    }

    @Override
    public List<Object> getPacketData(final List<Object> objects) {
        objects.add(facing.ordinal());

        return objects;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean canSetFacing(final EnumFacing facing) {
        return Arrays.asList(EnumFacing.HORIZONTALS).contains(facing);
    }

    @Override
    public EnumFacing getFacing() {
        return facing;
    }

    @Override
    public void setFacing(final EnumFacing facing) {
        this.facing = facing;

        NuclearPhysics.getPacketHandler().sendToReceivers(new PacketTileEntity(this), this);
    }
}
