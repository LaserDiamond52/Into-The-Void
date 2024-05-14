package net.laserdiamond.intothevoid.block;

import net.laserdiamond.intothevoid.block.entity.ITVSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.ArrayList;
import java.util.List;

public class ITVWallSignBlock extends WallSignBlock implements BlockTaggable {

    private final List<TagKey<Block>> blockTags;

    public ITVWallSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
        this.blockTags = new ArrayList<>();
    }

    public ITVWallSignBlock(Properties pProperties, WoodType pType, List<TagKey<Block>> blockTags) {
        super(pProperties, pType);
        this.blockTags = blockTags;
    }

    @Override
    public List<TagKey<Block>> getBlockTags() {
        return blockTags;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ITVSignBlockEntity(pPos, pState);
    }
}
