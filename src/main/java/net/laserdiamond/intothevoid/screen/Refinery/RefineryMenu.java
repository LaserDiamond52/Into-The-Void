package net.laserdiamond.intothevoid.screen.Refinery;

import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.block.entity.RefineryBlockEntity;
import net.laserdiamond.intothevoid.screen.ITVMenuTypes;
import net.laserdiamond.intothevoid.screen.Refinery.RefineryResultSlot;
import net.laserdiamond.intothevoid.screen.Refinery.RefineryWaterSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class RefineryMenu extends AbstractContainerMenu {

    public final RefineryBlockEntity be;
    private final Level level;
    private final ContainerData data;

    public RefineryMenu(int containerId, Inventory inv, FriendlyByteBuf extraData)
    {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(3));
    }

    private SlotItemHandler water, input, output;
    public RefineryMenu(int containerId, Inventory inv, BlockEntity blockEntity, ContainerData data)
    {
        super(ITVMenuTypes.REFINERY_MENU.get(), containerId);
        checkContainerSize(inv, 3);
        be = ((RefineryBlockEntity) blockEntity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler ->
        {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 56, 53)); // Input Slot
            water = new RefineryWaterSlot(iItemHandler, 1, 56, 17);
            this.addSlot(water); // Water Slot
            output = new RefineryResultSlot(iItemHandler, 2, 115, 35);
            this.addSlot(output); // Output Slot
        });

        addDataSlots(data);

    }

    public boolean isRefining()
    {
        return data.get(0) > 0;
    }

    public int getScaledProgress()
    {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INV_ROW_COUNT = 3;
    private static final int PLAYER_INV_COLUMN_COUNT = 9;
    private static final int PLAYER_INV_SLOT_COUNT = PLAYER_INV_ROW_COUNT * PLAYER_INV_COLUMN_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INV_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int REFINERY_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int REFINERY_INVENTORY_SLOT_COUNT = 3;

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        Slot sourceSlot = slots.get(i);
        if (sourceSlot == null || !sourceSlot.hasItem())
        {
            return ItemStack.EMPTY;
        }
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copySourceStack = sourceStack.copy();

        // Check if clicked in vanilla container slot
        if (i < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT)
        {
            // Clicked in vanilla container slot, so merge stack into the refinery inventory
            if (!moveItemStackTo(sourceStack, REFINERY_INVENTORY_FIRST_SLOT_INDEX, REFINERY_INVENTORY_FIRST_SLOT_INDEX + REFINERY_INVENTORY_SLOT_COUNT, false))
            {
                return ItemStack.EMPTY;
            }
            // TODO: Figure out how to prevent items from going into slots
            // We don't want to be able to quick move items into the result slot!
            // Water Bucket should be the only viable item for the second slot
            // Add a item tag to items that are able to be put into the refinery

        } else if (i < REFINERY_INVENTORY_FIRST_SLOT_INDEX + REFINERY_INVENTORY_SLOT_COUNT)
        {
            // Refinery inventory slot, so merge itemstack into the player's inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
            {
                return ItemStack.EMPTY;
            }
        } else
        {
            // Invalid Slot index
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0)
        {
            sourceSlot.set(ItemStack.EMPTY);
        } else
        {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(player, sourceStack);
        return copySourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, be.getBlockPos()),
                player, ITVBlocks.REFINERY.get());
    }

    private void addPlayerInventory(Inventory pInv)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlot(new Slot(pInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory pInv)
    {
        for (int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(pInv, i, 8 + i * 18, 142));
        }
    }
}
