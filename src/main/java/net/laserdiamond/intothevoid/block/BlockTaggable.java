package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * An interface used by block objects of this mod to give them tags
 */
public interface BlockTaggable {

    /**
     * A list of the tags that should be applied to the block
     * @return A list of tags that should be applied to the block
     */
    List<TagKey<Block>> getBlockTags();
}
