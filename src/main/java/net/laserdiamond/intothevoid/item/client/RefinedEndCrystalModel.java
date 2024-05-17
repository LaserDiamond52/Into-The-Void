package net.laserdiamond.intothevoid.item.client;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ingredients.refinedEndCrystal.RefinedEndCrystal;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RefinedEndCrystalModel extends GeoModel<RefinedEndCrystal> {
    @Override
    public ResourceLocation getModelResource(RefinedEndCrystal refinedEndCrystal) {
        return new ResourceLocation(IntoTheVoid.MODID, "geo/refined_end_crystal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RefinedEndCrystal refinedEndCrystal) {
        return new ResourceLocation(IntoTheVoid.MODID, "textures/item/refined_end_crystal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RefinedEndCrystal refinedEndCrystal) {
        return new ResourceLocation(IntoTheVoid.MODID, "animations/refined_end_crystal.animation.json");
    }
}
