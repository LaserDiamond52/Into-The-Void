package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;

import java.util.List;

/**
 * A sapling block of this mod
 */
public class ITVSaplingBlock extends SaplingBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVSaplingBlock(AbstractTreeGrower pTreeGrower, Properties pProperties, List<TagKey<Block>> blockTags) {
        super(pTreeGrower, pProperties);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
