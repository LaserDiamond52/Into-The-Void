package net.laserdiamond.intothevoid.item.client;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ingredients.refinedEndCrystal.RefinedEndCrystalItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/**
 * The Refined End Crystal item model
 */
public class RefinedEndCrystalModel extends GeoModel<RefinedEndCrystalItem> {

    /**
     * Gets the resource location of the model for this item
     * @param refinedEndCrystalItem The Refined End Crystal Item
     * @return The ResourceLocation mapping to the model
     */
    @Override
    public ResourceLocation getModelResource(RefinedEndCrystalItem refinedEndCrystalItem) {
        return new ResourceLocation(IntoTheVoid.MODID, "geo/refined_end_crystal.geo.json");
    }

    /**
     * Gets the resource location of the texture for this item
     * @param refinedEndCrystalItem The Refined End Crystal Item
     * @return The ResourceLocation mapping to the texture
     */
    @Override
    public ResourceLocation getTextureResource(RefinedEndCrystalItem refinedEndCrystalItem) {
        return new ResourceLocation(IntoTheVoid.MODID, "textures/item/refined_end_crystal.png");
    }

    /**
     * Gets the resource location of the animation for this item
     * @param refinedEndCrystalItem The Refined End Crystal Item
     * @return the ResourceLocation mapping ot the animation
     */
    @Override
    public ResourceLocation getAnimationResource(RefinedEndCrystalItem refinedEndCrystalItem) {
        return new ResourceLocation(IntoTheVoid.MODID, "animations/refined_end_crystal.animation.json");
    }
}
