package net.laserdiamond.intothevoid.worldgen.biome.surface;

import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.worldgen.biome.ITVBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

/**
 * Class that contains surface rules for the biomes of this mod
 */
public class ITVSurfaceRules {

    /**
     * A SurfaceRules ConditionSource that checks for water level
     */
    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    /**
     * A SurfaceRules ConditionSource that creates Null Sand
     */
    private static final SurfaceRules.RuleSource NULL_SAND = makeStateRule(ITVBlocks.NULL_SAND.get());

    /**
     * A SurfaceRules ConditionSource that creates Nullcelium
     */
    private static final SurfaceRules.RuleSource NULLCELIUM = makeStateRule(ITVBlocks.NULLCELIUM.get());

    /**
     * A SurfaceRules ConditionSource that creates End Stone
     */
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);

    //public static final SurfaceRules.RuleSource PURPUR_FOREST = SurfaceRules.ifTrue(SurfaceRules.isBiome(ITVBiomes.PURPUR_FOREST), SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ITVBlocks.NULLCELIUM.get().defaultBlockState()))));

    /**
     * Configures and creates the Surface rules for the Purpur Forest
     * @return A SurfaceRules RuleSource that contains the Surface Rules of the Purpur Forest
     */
    public static SurfaceRules.RuleSource makePurpurForestRules()
    {
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, NULLCELIUM), NULL_SAND);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ITVBiomes.PURPUR_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, NULL_SAND)),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, END_STONE)
        );
    }

    /**
     * Helper method used to help create the Surface Rules
     * @param block The block to be used in the Surface Rules
     * @return A SurfaceRules RuleSource of the Surface Rule
     */
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
