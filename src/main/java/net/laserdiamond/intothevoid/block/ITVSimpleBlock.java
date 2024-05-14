package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * Class that represents a simple block of this mod. Blocks of this child class will automatically have their model data generated
 */
public class ITVSimpleBlock extends Block implements BlockTaggable{

    private final List<TagKey<Block>> blockTags;

    public ITVSimpleBlock(Properties pProperties, List<TagKey<Block>> blockTags) {
        super(pProperties);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
