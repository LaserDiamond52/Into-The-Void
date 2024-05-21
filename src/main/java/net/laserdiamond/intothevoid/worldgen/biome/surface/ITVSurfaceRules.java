package net.laserdiamond.intothevoid.worldgen.biome.surface;

import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.worldgen.biome.ITVBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ITVSurfaceRules {

    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(ITVBlocks.NULL_SAND.get());
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(ITVBlocks.NULLCELIUM.get());
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.END_STONE);
    public static final SurfaceRules.RuleSource PURPUR_FOREST = SurfaceRules.ifTrue(SurfaceRules.isBiome(ITVBiomes.PURPUR_FOREST), SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ITVBlocks.NULLCELIUM.get().defaultBlockState()))));

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLvl = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLvl, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ITVBiomes.PURPUR_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        )));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
