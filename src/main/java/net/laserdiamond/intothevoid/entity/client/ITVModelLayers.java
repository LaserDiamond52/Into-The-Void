package net.laserdiamond.intothevoid.entity.client;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

/**
 * Contains the model layers for all entities of this mod
 */
public class ITVModelLayers {

    /**
     * ModelLayerLocation for the Purpur Wood Boat
     */
    public static final ModelLayerLocation PURPUR_WOOD_BOAT_LAYER = new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, "boat/purpur_wood"), "main");

    /**
     * ModelLayerLocation for the Purpur Wood Boat with a chest
     */
    public static final ModelLayerLocation PURPUR_WOOD_CHEST_BOAT_LAYER = new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, "chest_boat/purpur_wood"), "main");

    /**
     * ModelLayerLocation for the Void Pirate
     */
    public static final ModelLayerLocation VOID_PIRATE = new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, "void_pirate_layer"), "main");

    /**
     * ModelLayerLocation for the Ender Dragon Hatchling
     */
    public static final ModelLayerLocation ENDER_DRAGON_HATCHLING = new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, "ender_dragon_hatchling_layer"), "main");

    /**
     * ModelLayerLocation for the Evolved Endermite
     */
    public static final ModelLayerLocation EVOLVED_ENDERMITE = new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, "evolved_endermite_layer"), "main");

    /**
     * ModelLayerLocation for Watcher Boss
     */
    public static final ModelLayerLocation WATCHER_BOSS = new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, "watcher_layer"), "main");

    /**
     * ModelLayerLocation for Watcher Minion
     */
    public static final ModelLayerLocation WATCHER_MINION = new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, "watcher_minion"), "main");

    /**
     * Helper method for Model Layer Locations for basic entity models
     * @param path Name for the texture
     * @return The Model Layer Location for the model
     */
    private static ModelLayerLocation basicModelLayer(String path)
    {
        return new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, path), "main");
    }
}
