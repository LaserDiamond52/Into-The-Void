package net.laserdiamond.intothevoid.worldgen.biome;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

/**
 * Class that helps register biomes for Terrablender
 */
public class ITVTerrablender {

    /**
     * Registers biomes for use with Terrablender
     */
    public static void registerBiomes()
    {
        Regions.register(new ITVOverworldRegion(new ResourceLocation(IntoTheVoid.MODID, "overworld"), 5));
    }
}
