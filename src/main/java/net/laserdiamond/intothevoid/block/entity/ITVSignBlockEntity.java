package net.laserdiamond.intothevoid.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A Sign Block Entity of this mod
 */
public class ITVSignBlockEntity extends SignBlockEntity {

    public ITVSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ITVBlockEntities.ITV_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ITVBlockEntities.ITV_SIGN.get();
    }
}
