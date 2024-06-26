package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * A block that drops itself when mined
 */
public class ITVSelfDropBlock extends ITVSimpleBlock {

    public ITVSelfDropBlock(Properties pProperties, List<TagKey<Block>> miningTags) {
        super(pProperties, miningTags);
    }

}
