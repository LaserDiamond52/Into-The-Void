package net.laserdiamond.intothevoid.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * Class that contains methods used for ore placements
 */
public class ITVOrePlacements {

    /**
     * Helper method used to help add ore placement
     * @param countPlacement The amount of ore veins in the chunk of the world
     * @param heightRange The height range (y level) for the ore to appear in the world
     * @return A List of "PlacementModifier" objects for the ore placement
     */
    public static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange)
    {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    /**
     * Generates the ore as a common ore
     * @param count The amount of ore veins in the chunk of the world
     * @param heightRange The height range (y level) for the ore to appear in the world
     * @return A List of "PlacementModifier" objects for the ore placement
     */
    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange)
    {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    /**
     * Generates the ore as a rare ore
     * @param chance Chance of the ore generating
     * @param heightRange The height range (y level) for the ore to appear in the world
     * @return A List of "PlacementModifier" objects for the ore placement
     */
    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange)
    {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}