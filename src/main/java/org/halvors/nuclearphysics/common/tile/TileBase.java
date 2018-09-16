package org.halvors.nuclearphysics.common.tile;

import net.minecraft.tileentity.TileEntity;
import org.halvors.nuclearphysics.api.BlockPos;

public class TileBase extends TileEntity {
    private BlockPos pos;

//
//	@Override
//    public void readFromNBT(final NBTTagCompound tag) {
//        super.readFromNBT(tag);
//	    System.out.println("READNBT: "+ xCoord);
//
//        // Initialize BlockPos object with the actual coordinates.
//        pos = new BlockPos(xCoord, yCoord, zCoord);
//    }

    public BlockPos getPos() {
    	if(pos == null){
    		pos = new BlockPos(xCoord, yCoord, zCoord);
	    }
        return pos;
    }

}
