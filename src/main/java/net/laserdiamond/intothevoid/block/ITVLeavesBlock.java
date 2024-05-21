package net.laserdiamond.intothevoid.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ITVLeavesBlock extends LeavesBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVLeavesBlock(Properties pProperties) {
        super(pProperties);
        this.blockTags = new ArrayList<>();
    }

    public ITVLeavesBlock(Properties pProperties, List<TagKey<Block>> blockTags)
    {
        super(pProperties);
        this.blockTags = blockTags;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
