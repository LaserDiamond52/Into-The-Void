package net.laserdiamond.intothevoid.block;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.ArrayList;
import java.util.List;

public class ITVFenceGateBlock extends FenceGateBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVFenceGateBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
        this.blockTags = new ArrayList<>();
    }

    public ITVFenceGateBlock(Properties pProperties, SoundEvent openSound, SoundEvent closeSound) {
        super(pProperties, openSound, closeSound);
        this.blockTags = new ArrayList<>();
    }

    public ITVFenceGateBlock(Properties pProperties, SoundEvent openSound, SoundEvent closeSound, List<TagKey<Block>> blockTags)
    {
        super(pProperties, openSound, closeSound);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }
}
