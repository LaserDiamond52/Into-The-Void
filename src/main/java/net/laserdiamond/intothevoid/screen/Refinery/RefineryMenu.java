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
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
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

        // Add the input slots to the menu
        this.be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler ->
        {
            input = new RefineryInputSlot(iItemHandler, 0, 56, 53);
            this.addSlot(input); // Input Slot
            water = new RefineryWaterSlot(iItemHandler, 1, 56, 17);
            this.addSlot(water); // Water Slot
            output = new RefineryResultSlot(iItemHandler, 2, 116, 35);
            this.addSlot(output); // Output Slot
        });

        addDataSlots(data);

    }

    /**
     * Used to determine if refining is in progress
     * @return True if refining progress is > 0, otherwise false
     */
    public boolean isRefining()
    {
        return data.get(0) > 0;
    }

    /**
     * Used to determine how much of the progress arrow to draw when refining is in progress
     * @return The progress arrow size based on the current refinery progress
     */
    public int getScaledProgress()
    {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    /**
     * Used to determine how much water is in the refinery
     * @return The size of the water level based on the current water level in the refinery
     */
    public int getScaledWaterLevel()
    {
        int waterLevel = this.data.get(2);
        int maxWaterLevel = this.data.get(3);
        int waterLevelSize = 14;

        return (maxWaterLevel != 0 && waterLevel != 0) ? (waterLevel * waterLevelSize / maxWaterLevel) : 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INV_ROW_COUNT = 3;
    private static final int PLAYER_INV_COLUMN_COUNT = 9;
    private static final int PLAYER_INV_SLOT_COUNT = PLAYER_INV_ROW_COUNT * PLAYER_INV_COLUMN_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INV_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int REFINERY_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int REFINERY_INVENTORY_SLOT_COUNT = 3;

    /**
     * Responsible for the quick move action the player can use to move items in the inventory
     * @param player The player interacting with the inventory
     * @param i The index of the inventory slot
     * @return The ItemStack to place in the slot that is result of the movement
     */
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

    /**
     * Checks if the player still has valid access to the container
     * @param player The player accessing the container
     * @return True if player access is still valid, false otherwise
     */
    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, be.getBlockPos()),
                player, ITVBlocks.REFINERY.get());
    }

    /**
     * Adds the player inventory to the Refinery menu
     * @param pInv The player inventory
     */
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

    /**
     * Adds the player hotbar to the Refinery menu
     * @param pInv the player inventory
     */
    private void addPlayerHotbar(Inventory pInv)
    {
        for (int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(pInv, i, 8 + i * 18, 142));
        }
    }
}
