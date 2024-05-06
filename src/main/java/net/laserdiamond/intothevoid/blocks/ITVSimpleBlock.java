package net.laserdiamond.intothevoid.blocks;

import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * Class that represents a simple block of this mod. Blocks of this child class will automatically have their model data generated
 */
public class ITVSimpleBlock extends Block implements ToolTierToMine {

    private final TagKey<Block> miningTag;

    public ITVSimpleBlock(Properties pProperties, TagKey<Block> miningTag) {
        super(pProperties);
        this.miningTag = miningTag;
    }


    @Override
    public TagKey<Block> tagKey() {
        return miningTag;
    }
}
