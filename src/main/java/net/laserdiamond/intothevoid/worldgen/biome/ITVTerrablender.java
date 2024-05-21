package net.laserdiamond.intothevoid.worldgen.biome;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ITVTerrablender {

    public static void registerBiomes()
    {
        Regions.register(new ITVOverworldRegion(new ResourceLocation(IntoTheVoid.MODID, "overworld"), 5));
    }
}
