package net.laserdiamond.intothevoid.block.entity;

import net.laserdiamond.intothevoid.recipe.RefineryRecipe;
import net.laserdiamond.intothevoid.screen.Refinery.RefineryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * A Refinery Block Entity
 */
public class RefineryBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    public static final int ITEM_INPUT_SLOT = 0;
    public static final int WATER_INPUT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private int waterLevel = 0;
    private int maxWaterLevel = 100;

    public RefineryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        // Sets up the Refinery Block's data when placed

        super(ITVBlockEntities.REFINERY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i)
                {
                    case 0 -> RefineryBlockEntity.this.progress; // Progress of the refinery
                    case 1 -> RefineryBlockEntity.this.maxProgress; // Max progress of the refinery
                    case 2 -> RefineryBlockEntity.this.waterLevel; // The water level in the refinery
                    case 3 -> RefineryBlockEntity.this.maxWaterLevel; // The max water level for the refinery
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i)
                {
                    case 0 -> RefineryBlockEntity.this.progress = i1; // Progress of the refinery
                    case 1 -> RefineryBlockEntity.this.maxProgress = i1; // Max progress of the refinery
                    case 2 -> RefineryBlockEntity.this.waterLevel = i1; // The water level in the refinery
                    case 3 -> RefineryBlockEntity.this.maxWaterLevel = i1; // The max water level for the refinery
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    /**
     * Determines the drops of the Refinery when it is broken. When the Refinery is mined, it will drop all items that were in its container
     */
    public void drops()
    {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    /**
     * The display name of the block entity inside the inventory
     * @return A Component representing the display name
     */
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.into_the_void.refinery");
    }

    /**
     * Creates the Menu GUI for the block entity when interacted with
     * @param i The id of the inventory
     * @param inventory The inventory
     * @param player The player opening the menu
     * @return An AbstractContainerMenu of the menu to open
     */
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new RefineryMenu(i, inventory, this, this.data);
    }

    /**
     * Saves all data of the menu/inventory when interaction ends with it. This could be closing the menu, closing the world, etc.
     * @param pTag The CompoundTag
     */
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("refinery.progress", progress);
        pTag.putInt("refinery.water_level", waterLevel);
        super.saveAdditional(pTag);
    }

    /**
     * Loads the data of the menu when opened/interacted with
     * @param pTag The CompoundTag
     */
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));

        progress = pTag.getInt("refinery.progress");
        waterLevel = pTag.getInt("refinery.water_level");
    }

    /**
     * The tick function that runs every in-game tick for the Refinery. Gives functionality to the block
     * @param level The world/level the block is in
     * @param blockPos The position of the block
     * @param blockState The state of the block
     */
    public void tick(Level level, BlockPos blockPos, BlockState blockState)
    {
        if (itemHandler.getStackInSlot(WATER_INPUT_SLOT).getItem() == Items.WATER_BUCKET) // Check when a water bucket is placed in the water input slot
        {
            waterLevel = Math.min(maxWaterLevel, waterLevel + 50); // Add 50 to the water level. If it goes over the max, set the water level to the max
            itemHandler.setStackInSlot(WATER_INPUT_SLOT, new ItemStack(Items.BUCKET));
        }
        if (hasRecipeJson()) // Check if a valid recipe is inside the container
        {
            // Run the refinery process to complete the recipe
            increaseRefineryProgress();
            setChanged(level, blockPos, blockState);

            if (hasProgressFinished())
            {
                refineItem();
                resetRefineryProgress();
            }
        } else // Reset Refinery progress if at any point there is not a matching recipe inside the container
        {
            resetRefineryProgress();
        }
    }

    /**
     * Resets the progress of the refinery
     */
    private void resetRefineryProgress() {
        progress = 0;
    }

    /**
     * Consumes an ingredient item to output the result item stack
     */
    private void refineItem() {
        Optional<RefineryRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(ITEM_INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    /**
     * Determines if the refinery progress has been completed
     * @return True if progress >= maxProgress, false if not
     */
    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    /**
     * Increases the Refinery progress. When progress becomes equal to or greater than the max progress, 1 water level is removed
     */
    private void increaseRefineryProgress() {
        progress++; // Update progress
        if (progress >= maxProgress && waterLevel > 0)
        {
            waterLevel--; // Remove 1 from the water level if progress is complete and we have water
        }
    }

    /**
     * Determines if a valid recipe is present inside the Refinery based on the recipe Json files for this recipe type, and the water level in the refinery
     * @return True if a valid recipe was found, false if not.
     */
    private boolean hasRecipeJson()
    {
        Optional<RefineryRecipe> recipe = getCurrentRecipe(); // Use optional to get the recipe inside the container.

        // Optional can either return a value, or be empty

        if (recipe.isEmpty())
        {
            return false; // No valid recipe, end method
        }
        ItemStack result = recipe.get().getResultItem(null); // Get the result item
        boolean hasEnoughWater = waterLevel > 0; // Check if enough water is in the refinery

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem()) && hasEnoughWater;
    }

    /**
     * Gets the current recipe based on the item in the ingredient slot
     * @return an Optional of type "RefineryRecipe"
     */
    private Optional<RefineryRecipe> getCurrentRecipe() {

        SimpleContainer inv = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inv.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(RefineryRecipe.Type.INSTANCE, inv, level);
    }

    /**
     * Determines if the Refinery is able to output an item into the output slot
     * @param item The item present in the output slot
     * @return True if the item in the output slot is either empty or of the same crafting result as the input ingredient. False if not
     */
    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    /**
     * Determines whether there is enough room in the result item stack for an additional output item
     * @param count The amount of items to add to the result slot
     * @return True if the Refinery can output items to the result slot, false if not
     */
    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }


}
