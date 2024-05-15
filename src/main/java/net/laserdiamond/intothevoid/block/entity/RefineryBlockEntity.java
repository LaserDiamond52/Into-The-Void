package net.laserdiamond.intothevoid.block.entity;

import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.screen.RefineryMenu;
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

public class RefineryBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    private static final int ITEM_INPUT_SLOT = 0;
    private static final int WATER_INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private int waterLevel = 0;
    private int maxWaterLevel = 100;
    public RefineryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ITVBlockEntities.REFINERY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i)
                {
                    case 0 -> RefineryBlockEntity.this.progress;
                    case 1 -> RefineryBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i)
                {
                    case 0 -> RefineryBlockEntity.this.progress = i1;
                    case 1 -> RefineryBlockEntity.this.maxProgress = i1;
                }
            }

            @Override
            public int getCount() {
                return 3;
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

    public void drops()
    {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i <itemHandler.getSlots(); i++)
        {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.into_the_void.refinery");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new RefineryMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("refinery.progress", progress);
        pTag.putInt("refinery.water_level", waterLevel);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));

        progress = pTag.getInt("refinery.progress");
        waterLevel = pTag.getInt("refinery.water_level");
    }


    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasRecipe())
        {
            increaseRefineryProgress();
            setChanged(level, blockPos, blockState);

            if (hasProgressFinished())
            {
                refineItem();
                resetRefineryProgress();
            }
        } else
        {
            resetRefineryProgress();
        }
    }

    private void resetRefineryProgress() {
        progress = 0;
    }

    private void refineItem() {
        ItemStack result = new ItemStack(ITVItems.REFINED_LONSDALEITE.get());
        this.itemHandler.extractItem(ITEM_INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseRefineryProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        boolean hasRefineryItem = this.itemHandler.getStackInSlot(ITEM_INPUT_SLOT).getItem() == ITVItems.LONSDALEITE.get();
        ItemStack result = new ItemStack(ITVItems.REFINED_LONSDALEITE.get());

        return hasRefineryItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }


}
