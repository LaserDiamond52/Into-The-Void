package net.laserdiamond.intothevoid.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A wood log block of this mod
 */
public class ITVWoodLogBlock extends RotatedPillarBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;
    private final boolean isStripped;
    private final boolean isFlammable;
    private final int flammability;
    private final int fireSpreadSpeed;

    public ITVWoodLogBlock(Properties pProperties, List<TagKey<Block>> blockTags, boolean isStripped, boolean isFlammable, int flammability, int fireSpreadSpeed) {
        super(pProperties);
        this.blockTags = blockTags;
        this.isStripped = isStripped;
        this.isFlammable = isFlammable;
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }

    /**
     * Determines if the block is flammable
     * @param state The BlockState of the block
     * @param level BlockGetter
     * @param pos The Block position
     * @param direction The direction of the block
     * @return true if flammable, false otherwise
     */
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return isFlammable;
    }

    /**
     * Gets the flammability of the block
     * @param state The BlockState of the block
     * @param level BlockGetter
     * @param pos The Block Position
     * @param direction The direction of the block
     * @return An int denoting the flammability (higher = more flammable)
     */
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammability;
    }

    /**
     * Gets the fire spread speed (how fast fire spreads on the block)
     * @param state The BlockState of the block
     * @param level BlockGetter
     * @param pos The BlockPosition
     * @param direction The direction of the block
     * @return An int denoting how fast fire spreads on the block (higher = faster fire spread speed)
     */
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return fireSpreadSpeed;
    }

    /**
     * Allows player to strip log blocks of this mod, turning them into their stripped variants
     * @param state The BlockState of the block
     * @param context UseOnContext
     * @param toolAction The Tool Action being done to the block
     * @param simulate
     * @return The new block state of the block
     */
    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {

        if (context.getItemInHand().getItem() instanceof AxeItem) // Check if action is done with an axe
        {
            for (ITVBlocks.WoodBlocks woodBlocks : ITVBlocks.WoodBlocks.values()) // Loop through mod wood types
            {
                if (state.is(woodBlocks.getLogBlock().get())) // Check if player attempted to strip the log variant of the wood type
                {
                    return woodBlocks.getStrippedLogBlock().get().defaultBlockState().setValue(AXIS, state.getValue(AXIS)); // Change the block to the stripped variant without changing the axis of the block
                }
                if (state.is(woodBlocks.getWoodBlock().get())) // Check if player attempted to strip the wood variant of the wood type
                {
                    return woodBlocks.getStrippedWoodBlock().get().defaultBlockState().setValue(AXIS, state.getValue(AXIS)); // Change the block to the stripped variant without changing the axis of the block
                }
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }

    public boolean isStripped() {
        return isStripped;
    }


}
