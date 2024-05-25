package net.laserdiamond.intothevoid.block;

import net.laserdiamond.intothevoid.block.entity.ITVHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.ArrayList;
import java.util.List;

/**
 * A hanging sign block of this mod
 */
public class ITVHangingSignBlock extends CeilingHangingSignBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;
    public ITVHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
        this.blockTags = new ArrayList<>();
    }

    public ITVHangingSignBlock(Properties pProperties, WoodType pType, List<TagKey<Block>> blockTags)
    {
        super(pProperties, pType);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ITVHangingSignBlockEntity(pPos, pState);
    }
}
