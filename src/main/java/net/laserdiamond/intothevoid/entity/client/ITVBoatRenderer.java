package net.laserdiamond.intothevoid.entity.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.boat.ITVBoatEntity;
import net.laserdiamond.intothevoid.entity.boat.ITVChestBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class ITVBoatRenderer extends BoatRenderer {

    private final Map<ITVBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public ITVBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(ITVBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type, type -> Pair.of(new ResourceLocation(IntoTheVoid.MODID, getTextureLocation(type, pChestBoat)), this.createBoatModel(pContext, type, pChestBoat))));
    }

    /**
     * Gets the texture location for the boat as a String
     * @param pType The "Into The Void" boat type
     * @param chestBoat Whether the boat is a normal boat or a chest boat
     * @return The texture location of the boat texture as a String
     */
    public static String getTextureLocation(ITVBoatEntity.Type pType, boolean chestBoat)
    {
        return chestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    /**
     * Creates the model for the boat (Same model used for vanilla boats)
     * @param pContext The EntityRendererProvider context
     * @param pType The "Into The Void" boat type
     * @param pChestBoat Whether the boat is a normal boat or a chest boat
     * @return The boat model as a ListModel of type "Boat"
     */
    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, ITVBoatEntity.Type pType, boolean pChestBoat)
    {
        ModelLayerLocation modelLayerLocation = pChestBoat ? ITVBoatRenderer.createChestBoatModelName(pType) : ITVBoatRenderer.createBoatModelName(pType);
        ModelPart modelPart = pContext.bakeLayer(modelLayerLocation);
        return pChestBoat ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }

    /**
     * Creates the model name of the boat
     * @param pType The "Into The Void" boat type
     * @return A ModelLayerLocation of the boat type
     */
    public static ModelLayerLocation createBoatModelName(ITVBoatEntity.Type pType)
    {
        return createLocation("boat/" + pType.getName(), "main");
    }

    /**
     * Creates the mode name of the chest boat
     * @param pType The "Into The Void" chest boat type
     * @return A ModelLayerLocation of the chest boat type
     */
    public static ModelLayerLocation createChestBoatModelName(ITVBoatEntity.Type pType)
    {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    /**
     * Creates the location to get the boat model names
     * @param pPath The path
     * @param pModel The model
     * @return A ModelLayerLocation of the
     */
    private static ModelLayerLocation createLocation(String pPath, String pModel)
    {
        return new ModelLayerLocation(new ResourceLocation(IntoTheVoid.MODID, pPath), pModel);
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat)
    {
        if (boat instanceof ITVBoatEntity itvBoatEntity)
        {
            return this.boatResources.get(itvBoatEntity.getITVVariant());
        } else if (boat instanceof ITVChestBoatEntity itvChestBoatEntity)
        {
            return this.boatResources.get(itvChestBoatEntity.getITVVariant());
        } else
        {
            return null;
        }
    }
}
