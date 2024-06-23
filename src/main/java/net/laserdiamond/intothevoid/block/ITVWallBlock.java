package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;

import java.util.ArrayList;
import java.util.List;

public class ITVWallBlock extends WallBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVWallBlock(Properties pProperties) {
        super(pProperties);
        this.blockTags = new ArrayList<>();
    }

    public ITVWallBlock(Properties pProperties, List<TagKey<Block>> blockTags)
    {
        super(pProperties);
        this.blockTags = blockTags;
    }


    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
