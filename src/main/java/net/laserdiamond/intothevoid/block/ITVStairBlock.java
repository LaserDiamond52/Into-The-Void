package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ITVStairBlock extends StairBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVStairBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
        this.blockTags = new ArrayList<>();
    }

    public ITVStairBlock(Supplier<BlockState> state, Properties properties, List<TagKey<Block>> blockTags)
    {
        super(state, properties);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
