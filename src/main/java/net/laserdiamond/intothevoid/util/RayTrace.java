package net.laserdiamond.intothevoid.util;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class RayTrace {

    /*
    @Deprecated
    public static <T extends Entity> List<T> rayTraceEntities(Level level, Vec3 pos, Vec3 ray, Optional<Predicate<T>> entityFilter, Class<T> entityClazz)
    {
        Vec3 end = pos.add(new Vec3(1,1,1));
        AABB aabb = new AABB(pos, end).expandTowards(ray);
        Vec3 checkVec = pos.add(ray);

        List<T> ret = new ArrayList<>();

        for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
        {
            AABB entityBB = t.getBoundingBox();

            if (entityBB.intersects(pos, checkVec))
            {
                ret.add(t);
            }

        }
        return ret;
    }


    @Deprecated
    public static <T extends Entity> List<T> rayTraceEntities(Level level, Vec3 pos, Vec3 ray, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, SimpleParticleType particleType)
    {
        Vec3 end = pos.add(new Vec3(1,1,1));
        AABB aabb = new AABB(pos, end).expandTowards(ray);
        Vec3 checkVec = pos.add(ray);

        level.addParticle(ParticleTypes.REVERSE_PORTAL, checkVec.x, checkVec.y, checkVec.z, 0,0,0);


        level.addParticle(particleType, pos.x, pos.y, pos.z, 0,0,0);
        level.addParticle(particleType, checkVec.x, checkVec.y, checkVec.z, 0,0,0);

        List<T> ret = new ArrayList<>();

        for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
        {
            AABB entityBB = t.getBoundingBox();

            if (entityBB.intersects(pos, checkVec))
            {
                ret.add(t);
            }

        }
        return ret;
    }
    */

    // NOTE: Use Mth.floor(targetEyePos.subtract(watcherEyePos) + *int* as distance to ray trace to the player
    // Vec3 rayCast should scale the normalized targetEyePos and scale by the iteration of the loop as well

    /**
     * Ray traces a path in the direction of the ray {@link Vec3} variable. Any entities hit by the ray trace are returned.
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray trace from
     * @param ray The direction of the ray
     * @param stepIncrement The step increment of the ray trace
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param distance The distance of the ray trace. Final distance is this parameter * stepIncrement
     * @param pierceBlocks Whether the ray trace should continue through blocks
     * @param pierceEntities Whether the ray trace should continue after hitting the first {@link Entity}
     * @return A List of the entities hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<T> rayTraceEntities(ServerLevel level, Vec3 pos, Vec3 ray, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int distance, boolean pierceBlocks, boolean pierceEntities)
    {
        Vec3 normRay = ray.normalize();
        List<T> ret = new ArrayList<>();

        for (double i = 1; i < distance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normRay.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            if (!pierceBlocks)
            {
                BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
                BlockState blockState = level.getBlockState(blockPos);
                if (blockState.isSolid())
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !ret.contains(t))
                {
                    ret.add(t);
                    if (!pierceEntities)
                    {
                        return ret;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Ray traces a path in the direction of the ray {@link Vec3} variable. Any entities hit by the ray trace are returned.
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray trace from
     * @param ray The direction of the ray
     * @param stepIncrement The step increment of the ray trace
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param distance The distance of the ray trace. Final distance is this parameter * stepIncrement
     * @param pierceBlocks Whether the ray trace should continue through blocks
     * @param pierceEntities Whether the ray trace should continue after hitting the first {@link Entity}
     * @param particleOptions The particles to display during the ray trace
     * @return A List of the entities hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<T> rayTraceEntities(ServerLevel level, Vec3 pos, Vec3 ray, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int distance, boolean pierceBlocks, boolean pierceEntities, ParticleOptions particleOptions)
    {
        Vec3 normRay = ray.normalize();
        List<T> ret = new ArrayList<>();

        for (double i = 1; i < distance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normRay.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            if (!pierceBlocks)
            {
                BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
                BlockState blockState = level.getBlockState(blockPos);
                if (blockState.isSolid())
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !ret.contains(t))
                {
                    ret.add(t);
                    if (!pierceEntities)
                    {
                        return ret;
                    }
                }
            }
            level.sendParticles(particleOptions, rayCast.x, rayCast.y, rayCast.z, 1, 0.0,0.0,0.0, 0.0);
        }

        return ret;
    }

    /**
     * Ray traces a path towards the destination {@link Vec3} variable. Any entities hit by the ray trace are returned.
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray from
     * @param destination The {@link Vec3} destination to ray trace to
     * @param stepIncrement The step increment of the ray trace
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param overShootDistance The distance to overshoot for after reaching the destination {@link Vec3}
     * @param pierceBlocks Whether the ray trace should continue through blocks
     * @param pierceEntities Whether they ray trace should continue after hitting the first {@link Entity}
     * @return A List of the entities hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<T> rayTraceToVecEntities(ServerLevel level, Vec3 pos, Vec3 destination, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int overShootDistance, boolean pierceBlocks, boolean pierceEntities)
    {
        Vec3 sub = destination.subtract(pos);
        Vec3 normDestination = sub.normalize();
        List<T> ret = new ArrayList<>();

        for (double i = 1; i < Mth.floor(sub.length()) + overShootDistance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normDestination.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            if (!pierceBlocks)
            {
                BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
                BlockState blockState = level.getBlockState(blockPos);
                if (blockState.isSolid())
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !ret.contains(t))
                {
                    ret.add(t);
                    if (!pierceEntities)
                    {
                        return ret;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Ray traces a path towards the destination {@link Vec3} variable. Any entities hit by the ray trace are returned.
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray from
     * @param destination The {@link Vec3} destination to ray trace to
     * @param stepIncrement The step increment of the ray trace
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param overShootDistance The distance to overshoot for after reaching the destination {@link Vec3}
     * @param pierceBlocks Whether the ray trace should continue through blocks
     * @param pierceEntities Whether they ray trace should continue after hitting the first {@link Entity}
     * @param particleOptions The particles to display during the ray trace
     * @return A List of the entities hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<T> rayTraceToVecEntities(ServerLevel level, Vec3 pos, Vec3 destination, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int overShootDistance, boolean pierceBlocks, boolean pierceEntities, ParticleOptions particleOptions)
    {
        Vec3 sub = destination.subtract(pos);
        Vec3 normDestination = sub.normalize();
        List<T> ret = new ArrayList<>();

        for (double i = 1; i < Mth.floor(sub.length()) + overShootDistance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normDestination.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            if (!pierceBlocks)
            {
                BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
                BlockState blockState = level.getBlockState(blockPos);
                if (blockState.isSolid())
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !ret.contains(t))
                {
                    ret.add(t);
                    if (!pierceEntities)
                    {
                        return ret;
                    }
                }
            }
            level.sendParticles(particleOptions, rayCast.x, rayCast.y, rayCast.z, 1, 0.0,0.0,0.0, 0.0);
        }

        return ret;
    }

    /**
     * Ray traces a path in the direction of the {@link Vec3} variable. Any block states hit by the ray trace are returned
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray from
     * @param ray The direction of the ray
     * @param stepIncrement The step increment of the ray
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param distance The distance of the ray trace. Final distance is this parameter * stepIncrement
     * @param pierceBlocks Whether the ray should continue through blocks
     * @param pierceEntities Whether the ray should continue after hitting the first {@link Entity}
     * @return A List of the {@link BlockState}s hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<BlockState> rayTraceBlockStates(ServerLevel level, Vec3 pos, Vec3 ray, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int distance, boolean pierceBlocks, boolean pierceEntities)
    {
        Vec3 normRay = ray.normalize();
        List<BlockState> ret = new ArrayList<>();

        for (double i = 1; i < distance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normRay.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
            BlockState blockState = level.getBlockState(blockPos);
            if (!ret.contains(blockState))
            {
                ret.add(blockState);
                if (!pierceBlocks && blockState.isSolid())
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !pierceEntities)
                {
                    return ret;
                }
            }
        }

        return ret;
    }

    /**
     * Ray traces a path in the direction of the {@link Vec3} variable. Any block states hit by the ray trace are returned
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray from
     * @param ray The direction of the ray
     * @param stepIncrement The step increment of the ray
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param distance The distance of the ray trace. Final distance is this parameter * stepIncrement
     * @param pierceBlocks Whether the ray should continue through blocks
     * @param pierceEntities Whether the ray should continue after hitting the first {@link Entity}
     * @param particleOptions The particles to display during the ray trace
     * @return A List of the {@link BlockState}s hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<BlockState> rayTraceBlockStates(ServerLevel level, Vec3 pos, Vec3 ray, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int distance, boolean pierceBlocks, boolean pierceEntities, ParticleOptions particleOptions)
    {
        Vec3 normRay = ray.normalize();
        List<BlockState> ret = new ArrayList<>();

        for (double i = 1; i < distance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normRay.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
            BlockState blockState = level.getBlockState(blockPos);
            if (!ret.contains(blockState))
            {
                ret.add(blockState);
                if (!pierceBlocks)
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !pierceEntities)
                {
                    return ret;
                }
            }
            level.sendParticles(particleOptions, rayCast.x, rayCast.y, rayCast.z, 1, 0.0,0.0,0.0, 0.0);
        }

        return ret;
    }

    /**
     * Ray traces a path towards the destination {@link Vec3} variable. Any block states hit by the ray trace are returned
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray from
     * @param destination The {@link Vec3} destination to ray trace to
     * @param stepIncrement The step increment of the ray
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param overShootDistance The distance of the ray trace. Final distance is this parameter * stepIncrement
     * @param pierceBlocks Whether the ray should continue through blocks
     * @param pierceEntities Whether the ray should continue after hitting the first {@link Entity}
     * @return A List of the {@link BlockState}s hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<BlockState> rayTraceToVecBlockStates(ServerLevel level, Vec3 pos, Vec3 destination, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int overShootDistance, boolean pierceBlocks, boolean pierceEntities)
    {
        Vec3 sub = destination.subtract(pos);
        Vec3 normDestination = sub.normalize();
        List<BlockState> ret = new ArrayList<>();

        for (double i = 1; i < Mth.floor(sub.length()) + overShootDistance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normDestination.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
            BlockState blockState = level.getBlockState(blockPos);
            if (!ret.contains(blockState))
            {
                ret.add(blockState);
                if (!pierceBlocks)
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !pierceEntities)
                {
                    return ret;
                }
            }

        }

        return ret;
    }

    /**
     * Ray traces a path towards the destination {@link Vec3} variable. Any block states hit by the ray trace are returned
     * @param level The {@link ServerLevel} of the Minecraft world
     * @param pos The {@link Vec3} position to start the ray from
     * @param destination The {@link Vec3} destination to ray trace to
     * @param stepIncrement The step increment of the ray
     * @param entityFilter The entity filter
     * @param entityClazz The {@link Entity} class to target for
     * @param overShootDistance The distance of the ray trace. Final distance is this parameter * stepIncrement
     * @param pierceBlocks Whether the ray should continue through blocks
     * @param pierceEntities Whether the ray should continue after hitting the first {@link Entity}
     * @param particleOptions The particles to display during the ray trace
     * @return A List of the {@link BlockState}s hit by the ray trace
     * @param <T> The type of {@link Entity}
     */
    public static <T extends Entity> List<BlockState> rayTraceToVecBlockStates(ServerLevel level, Vec3 pos, Vec3 destination, double stepIncrement, Optional<Predicate<T>> entityFilter, Class<T> entityClazz, int overShootDistance, boolean pierceBlocks, boolean pierceEntities, ParticleOptions particleOptions)
    {
        Vec3 sub = destination.subtract(pos);
        Vec3 normDestination = sub.normalize();
        List<BlockState> ret = new ArrayList<>();

        for (double i = 1; i < Mth.floor(sub.length()) + overShootDistance; i += stepIncrement)
        {
            Vec3 rayCast = pos.add(normDestination.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
            BlockState blockState = level.getBlockState(blockPos);
            if (!ret.contains(blockState))
            {
                ret.add(blockState);
                if (!pierceBlocks)
                {
                    return ret;
                }
            }

            for (T t : level.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = t.getBoundingBox();
                if (entityBB.intersects(aabb) && !pierceEntities)
                {
                    return ret;
                }
            }
            level.sendParticles(particleOptions, rayCast.x, rayCast.y, rayCast.z, 1, 0.0,0.0,0.0, 0.0);

        }

        return ret;
    }
}
