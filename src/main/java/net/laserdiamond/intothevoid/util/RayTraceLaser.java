package net.laserdiamond.intothevoid.util;

import com.google.common.base.Predicates;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Builder class for creating ray tracing (lasers)
 *
 *
 *
 * @param <T> The {@link Entity} type
 */
public class RayTraceLaser<T extends Entity> {

    private final ServerLevel serverLevel;
    private final Vec3 startPos;
    private double stepIncrement;
    private final Optional<Predicate<T>> entityFilter;
    private final Class<T> entityClazz;
    private final List<Class<? extends Block>> blockClazzes;
    private SimpleParticleType particle;
    private boolean pierceBlocks;
    private boolean pierceEntities;
    private final List<T> hitEntities;
    private final List<BlockState> hitBlockStates;

    /**
     * Constructor for ray trace. By default, the particle is null, pierceBlocks is false, and pierceEntities is false.
     * @param level The {@link ServerLevel}
     * @param startPos The starting position vector for the ray trace
     * @param entityFilter Entities to blacklist when collecting entities hit by the ray trace
     * @param entityClazz The entity class to search for
     * @param blockClazzes The block classes to blacklist when collecting blocks hit by the ray trace
     */
    public RayTraceLaser(ServerLevel level, Vec3 startPos, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, List<Class<? extends Block>> blockClazzes)
    {
        this.hitEntities = new ArrayList<>();
        this.hitBlockStates = new ArrayList<>();
        this.serverLevel = level;
        this.startPos = startPos;
        this.stepIncrement = 0.5;
        this.entityFilter = entityFilter;
        this.entityClazz = entityClazz;
        this.blockClazzes = blockClazzes;
        this.particle = null;
        this.pierceBlocks = false;
        this.pierceEntities = false;
    }

    /**
     * Allows the ray cast to continue through blocks
     * @return {@link RayTraceLaser}
     */
    public RayTraceLaser<T> setCanPierceBlocks()
    {
        this.pierceBlocks = true;
        return this;
    }

    /**
     * Allows the ray cast to continue through entities
     * @return {@link RayTraceLaser}
     */
    public RayTraceLaser<T> setCanPierceEntities()
    {
        this.pierceEntities = true;
        return this;
    }

    /**
     * Sets the step increment for the ray cast
     * @param stepIncrement The step increment for the ray cast
     * @return {@link RayTraceLaser}
     */
    public RayTraceLaser<T> setStepIncrement(double stepIncrement)
    {
        this.stepIncrement = stepIncrement;
        return this;
    }

    /**
     * Sets the particles to be displayed at each step of the ray cast
     * @param particle The particle to display at each step of the ray cast
     * @return {@link RayTraceLaser}
     */
    public RayTraceLaser<T> setParticle(SimpleParticleType particle)
    {
        this.particle = particle;
        return this;
    }

    /**
     * Fires the ray cast in the direction of the {@link Vec3} ray
     * @param ray The direction to fire the ray in
     * @param distance The distance of the ray cast
     */
    public RayTraceLaser<T> fireInDirection(Vec3 ray, int distance)
    {
        Vec3 normRay = ray.normalize();
        rayCast(normRay, distance);
        return this;
    }

    /**
     * Fires the ray cast to the {@link Vec3} destination
     * @param destination The destination for the ray cast
     * @param overshootDistance Distance to overshoot after reaching the destination
     */
    public RayTraceLaser<T> fireAtVec3D(Vec3 destination, int overshootDistance)
    {
        Vec3 sub = destination.subtract(startPos);
        Vec3 normDestination = sub.normalize();
        rayCast(normDestination, Mth.floor(sub.length()) + overshootDistance);
        return this;
    }

    /**
     * Logic for the ray cast
     * @param rayCastVec {@link Vec3}
     * @param distance The distance for the ray cast to travel through
     */
    private void rayCast(Vec3 rayCastVec, double distance)
    {
        for (double i = 0; i < distance; i += stepIncrement)
        {
            Vec3 rayCast = startPos.add(rayCastVec.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
            BlockState blockState = serverLevel.getBlockState(blockPos);
            Block hitBlock = blockState.getBlock();

            if (!blockClazzes.contains(hitBlock.getClass()))
            {
                hitBlockStates.add(blockState);
            }

            if (!hitBlockStates.contains(blockState))
            {
                hitBlockStates.add(blockState);
            }

            if (!pierceBlocks && blockState.isSolid())
            {
                return; // Pierce blocks is false and the blockState hit is solid
            }

            for (T t : serverLevel.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !hitEntities.contains(t))
                {
                    hitEntities.add(t);
                    if (!pierceEntities)
                    {
                        return; // Pierce entities is false and entity was hit
                    }
                }
            }

            if (particle != null)
            {
                serverLevel.sendParticles(particle, rayCast.x, rayCast.y, rayCast.z, 1,0.0,0.0,0.0,0.0);
            }
        }
    }

    public List<T> getHitEntities() {
        return hitEntities;
    }

    public List<BlockState> getHitBlockStates() {
        return hitBlockStates;
    }
}
