package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;

public class ITVTrapDoorBlock extends TrapDoorBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVTrapDoorBlock(Properties pProperties, BlockSetType pType) {
        super(pProperties, pType);
        this.blockTags = new ArrayList<>();
    }

    public ITVTrapDoorBlock(Properties pProperties, BlockSetType pType, List<TagKey<Block>> blockTags) {
        super(pProperties, pType);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
