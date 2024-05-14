package net.laserdiamond.intothevoid.block;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.ArrayList;
import java.util.List;

public class ITVPressurePlateBlock extends PressurePlateBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;
    public ITVPressurePlateBlock(Sensitivity pSensitivity, Properties pProperties, BlockSetType pType) {
        super(pSensitivity, pProperties, pType);
        this.blockTags = new ArrayList<>();
    }

    public ITVPressurePlateBlock(Sensitivity pSensitivity, Properties pProperties, BlockSetType pType, List<TagKey<Block>> blockTags)
    {
        super(pSensitivity, pProperties, pType);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
