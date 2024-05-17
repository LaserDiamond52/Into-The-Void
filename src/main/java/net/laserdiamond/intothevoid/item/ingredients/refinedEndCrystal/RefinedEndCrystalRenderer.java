package net.laserdiamond.intothevoid.item.ingredients.refinedEndCrystal;

import net.laserdiamond.intothevoid.item.client.RefinedEndCrystalModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class RefinedEndCrystalRenderer extends GeoItemRenderer<RefinedEndCrystal> {
    public RefinedEndCrystalRenderer() {
        super(new RefinedEndCrystalModel());
    }
}
