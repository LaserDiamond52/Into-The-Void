package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * A fence block of this mod
 */
public class ITVFenceBlock extends FenceBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVFenceBlock(Properties pProperties) {
        super(pProperties);
        this.blockTags = new ArrayList<>();
    }

    public ITVFenceBlock(Properties pProperties, List<TagKey<Block>> blockTags)
    {
        super(pProperties);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
