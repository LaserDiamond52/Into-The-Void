package net.laserdiamond.intothevoid.dataGen;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.worldgen.ITVBiomeModifiers;
import net.laserdiamond.intothevoid.worldgen.ITVConfiguredFeatures;
import net.laserdiamond.intothevoid.worldgen.ITVPlacedFeatures;
import net.laserdiamond.intothevoid.worldgen.biome.ITVBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * The world gen provider of this mod. Responsible for creating the configured features, placed features, biome modifiers, and most biomes
 */
public class ITVWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ITVConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ITVPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ITVBiomeModifiers::boostrap)
            .add(Registries.BIOME, ITVBiomes::bootstrap);

    public ITVWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(IntoTheVoid.MODID));
    }
}
