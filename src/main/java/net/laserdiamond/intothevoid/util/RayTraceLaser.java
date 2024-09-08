package net.laserdiamond.intothevoid.util;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class RayTraceLaser <T extends Entity, B extends Block> {

    private final List<T> hitEntities;
    private final List<B> hitBlocks;
    private final List<BlockState> hitBlockStates;

    @SuppressWarnings("unchecked")
    public RayTraceLaser(ServerLevel level, Vec3 pos, Vec3 ray, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, Class<B> blockClazz, int distance, boolean pierceBlocks, boolean pierceEntities, ParticleOptions particleOptions)
    {
        hitEntities = new ArrayList<>();
        hitBlocks = new ArrayList<>();
        hitBlockStates = new ArrayList<>();

        Vec3 normRay = ray.normalize();

        for (double i = 1; i < distance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normRay.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
            BlockState blockState = level.getBlockState(blockPos);

            if (blockState.getBlock().getClass() == blockClazz)
            {
                hitBlocks.add((B) blockState.getBlock());
                hitBlockStates.add(blockState);
            }

            if (!pierceBlocks && blockState.isSolid())
            {
                break;
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !hitEntities.contains(t))
                {
                    hitEntities.add(t);
                    if (!pierceEntities)
                    {
                        break;
                    }
                }
            }

            level.sendParticles(particleOptions, rayCast.x, rayCast.y, rayCast.z,1,0.0,0.0,0.0,0.0);
        }
    }

    public List<T> getHitEntities() {
        return hitEntities;
    }

    public List<B> getHitBlocks()
    {
        return hitBlocks;
    }

    public List<BlockState> getHitBlockStates() {
        return hitBlockStates;
    }
}
