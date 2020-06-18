package org.halvors.nuclearphysics.common.capabilities.fluid;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.util.List;

public class LiquidTank extends FluidTank {
    public LiquidTank(final int capacity) {
        super(capacity);
    }

    public void handlePacketData(final ByteBuf dataStream) {
        if (dataStream.readBoolean()) {
            setFluid(FluidStack.loadFluidStackFromNBT(ByteBufUtils.readTag(dataStream)));
        } else {
            setFluid(null);
        }
    }

    public List<Object> getPacketData(final List<Object> objects) {
        if (fluid != null) {
            objects.add(true);

            final NBTTagCompound compoundInputTank = new NBTTagCompound();
            fluid.writeToNBT(compoundInputTank);
            objects.add(compoundInputTank);
        } else {
            objects.add(false);
        }

        return objects;
    }
}