package net.laserdiamond.intothevoid.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A Hanging Sign Block Entity of this mod
 */
public class ITVHangingSignBlockEntity extends SignBlockEntity {

    public ITVHangingSignBlockEntity(BlockPos pBlockPos, BlockState pBlockState) {
        super(ITVBlockEntities.ITV_HANGING_SIGN.get(), pBlockPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ITVBlockEntities.ITV_HANGING_SIGN.get();
    }
}
