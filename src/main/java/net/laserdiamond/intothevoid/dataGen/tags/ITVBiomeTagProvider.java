package net.laserdiamond.intothevoid.dataGen.tags;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.util.ITVTags;
import net.laserdiamond.intothevoid.worldgen.biome.ITVBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * The biome tag provider of this mod. Doesn't work on biomes of this mod at the moment
 */
public class ITVBiomeTagProvider extends BiomeTagsProvider {

    public ITVBiomeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, providerCompletableFuture, IntoTheVoid.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        //this.tag(ITVTags.Biomes.PURPUR_FOREST).add(ITVBiomes.PURPUR_FOREST);

    }
}
