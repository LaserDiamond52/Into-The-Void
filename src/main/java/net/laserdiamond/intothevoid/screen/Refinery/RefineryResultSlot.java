package net.laserdiamond.intothevoid.screen.Refinery;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Class that represents the Refinery result slot. No items can be placed in this slot (by the player), only removed.
 */
public class RefineryResultSlot extends SlotItemHandler {

    public RefineryResultSlot(IItemHandler iItemHandler, int pSlot, int pX, int pY) {
        super(iItemHandler, pSlot, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return false;
    }
}
