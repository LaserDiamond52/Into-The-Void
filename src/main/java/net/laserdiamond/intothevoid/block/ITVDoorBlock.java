package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;

/**
 * A door block of this mod
 */
public class ITVDoorBlock extends DoorBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;
    public ITVDoorBlock(Properties pProperties, BlockSetType pType) {
        super(pProperties, pType);
        this.blockTags = new ArrayList<>();
    }

    public ITVDoorBlock(Properties pProperties, BlockSetType pType, List<TagKey<Block>> blockTags)
    {
        super(pProperties, pType);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
