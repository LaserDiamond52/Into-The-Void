package net.laserdiamond.intothevoid.worldgen.biome.surface;

import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.worldgen.biome.ITVBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ITVSurfaceRules {

    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    private static final SurfaceRules.RuleSource NULL_SAND = makeStateRule(ITVBlocks.NULL_SAND.get());
    private static final SurfaceRules.RuleSource NULLCELIUM = makeStateRule(ITVBlocks.NULLCELIUM.get());
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);
    public static final SurfaceRules.RuleSource PURPUR_FOREST = SurfaceRules.ifTrue(SurfaceRules.isBiome(ITVBiomes.PURPUR_FOREST), SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ITVBlocks.NULLCELIUM.get().defaultBlockState()))));

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLvl = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLvl, NULLCELIUM), NULL_SAND);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ITVBiomes.PURPUR_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, NULL_SAND)),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, END_STONE)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
