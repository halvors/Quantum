package org.halvors.nuclearphysics.common.container.machine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.halvors.nuclearphysics.common.container.ContainerBase;
import org.halvors.nuclearphysics.common.init.ModItems;
import org.halvors.nuclearphysics.common.tile.machine.TileQuantumAssembler;

public class ContainerQuantumAssembler extends ContainerBase<TileQuantumAssembler> {

	public static class DarkMatterSlot extends Slot {

		public DarkMatterSlot(IInventory inventory, int slot, int xPos, int yPos) {
			super(inventory, slot, xPos, yPos);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return stack.getItem() == ModItems.itemDarkMatterCell;
		}
	}

	public ContainerQuantumAssembler(final InventoryPlayer inventoryPlayer, final TileQuantumAssembler tile) {
		super(7, inventoryPlayer, tile);

		yInventoryDisplacement = 148;
		yHotBarDisplacement = 206;
		addSlotToContainer(new DarkMatterSlot(tile, 0, 80, 40));
		addSlotToContainer(new DarkMatterSlot(tile, 1, 53, 56));
		addSlotToContainer(new DarkMatterSlot(tile, 2, 107, 56));
		addSlotToContainer(new DarkMatterSlot(tile, 3, 53, 88));
		addSlotToContainer(new DarkMatterSlot(tile, 4, 107, 88));
		addSlotToContainer(new DarkMatterSlot(tile, 5, 80, 103));
		addSlotToContainer(new Slot(tile, 6, 80, 72));

		// Player inventory
		addPlayerInventory(inventoryPlayer.player);
	}

	@Override
	protected boolean mergeItemStack(ItemStack itemStack, int min, int max, boolean negative) {
		if (itemStack.getItem() == ModItems.itemDarkMatterCell && min <= 6) {
			return false;
		}
		return super.mergeItemStack(itemStack, min, max, negative);
	}

}
