package net.laserdiamond.intothevoid.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
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

public class ITVWoodLogBlock extends RotatedPillarBlock {

    private final List<TagKey<Block>> blockTags;
    private final boolean isStripped;

    public ITVWoodLogBlock(Properties pProperties, List<TagKey<Block>> blockTags, boolean isStripped) {
        super(pProperties);
        this.blockTags = blockTags;
        this.isStripped = isStripped;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {

        if (context.getItemInHand().getItem() instanceof AxeItem)
        {
            if (state.is(ITVBlocks.CHORUS_LOG.get()))
            {
                return ITVBlocks.STRIPPED_CHORUS_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ITVBlocks.CHORUS_WOOD.get()))
            {
                return ITVBlocks.STRIPPED_CHORUS_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }

    public boolean isStripped() {
        return isStripped;
    }
}
