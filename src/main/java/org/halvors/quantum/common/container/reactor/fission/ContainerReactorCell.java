package org.halvors.quantum.common.container.reactor.fission;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.halvors.quantum.common.item.reactor.fission.ItemBreederFuel;
import org.halvors.quantum.common.item.reactor.fission.ItemFissileFuel;
import org.halvors.quantum.common.tile.reactor.fission.TileReactorCell;
import org.halvors.quantum.lib.gui.ContainerBase;
import org.halvors.quantum.lib.gui.slot.SlotSpecific;

public class ContainerReactorCell extends ContainerBase {
    public ContainerReactorCell(InventoryPlayer inventoryPlayer, TileReactorCell tileEntity) {
        super(tileEntity);

        addSlotToContainer(new SlotSpecific(tileEntity, 0, 79, 17, ItemFissileFuel.class, ItemBreederFuel.class));
        addPlayerInventory(inventoryPlayer.player);
    }

    /** Called to transfer a stack from one inventory to the other eg. when shift clicking. */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        ItemStack copyStack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            copyStack = itemStack.copy();

            if (slotId >= slotCount) {
                if (getSlot(0).isItemValid(itemStack)) {
                    if (!mergeItemStack(itemStack, 0, 1, false)) {
                        return null;
                    }
                } else if (slotId < 27 + slotCount) {
                    if (!mergeItemStack(itemStack, 27 + slotCount, 36 + slotCount, false)) {
                        return null;
                    }
                } else if (slotId >= 27 + slotCount && slotId < 36 + slotCount && !mergeItemStack(itemStack, 4, 30, false)) {
                    return null;
                }
            } else if (!mergeItemStack(itemStack, slotCount, 36 + slotCount, false)) {
                return null;
            }

            if (itemStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack.stackSize == copyStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemStack);
        }

        return copyStack;
    }
}