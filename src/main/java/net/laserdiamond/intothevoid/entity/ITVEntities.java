package net.laserdiamond.intothevoid.entity;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.boat.ITVBoatEntity;
import net.laserdiamond.intothevoid.entity.boat.ITVChestBoatEntity;
import net.laserdiamond.intothevoid.entity.itv.EnderDragonHatchlingEntity;
import net.laserdiamond.intothevoid.entity.itv.EvolvedEndermiteEntity;
import net.laserdiamond.intothevoid.entity.itv.VoidPirateEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ITVEntities {

    /**
     * A DeferredRegister of type "EntityType" of an unknown type that registers all entities of this mod (Does not include block entities)
     */
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, IntoTheVoid.MODID);

    /**
     * A RegistryObject of type "EntityType" of type "ITVBoatEntity" that represents a Purpur Wood Boat
     */
    public static final RegistryObject<EntityType<ITVBoatEntity>> PURPUR_WOOD_BOAT = ENTITY_TYPES.register("purpur_wood_boat",
            () -> EntityType.Builder.<ITVBoatEntity>of(ITVBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("purpur_wood_boat"));

    /**
     * A RegistryObject of type "EntityType" of type "ITVChestBoatEntity" that represents a Purpur Wood Boat with a chest
     */
    public static final RegistryObject<EntityType<ITVChestBoatEntity>> PURPUR_WOOD_CHEST_BOAT = ENTITY_TYPES.register("purpur_wood_chest_boat",
            () -> EntityType.Builder.<ITVChestBoatEntity>of(ITVChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("purpur_wood_chest_boat"));

    /**
     * A RegistryObject of type "EntityType" of type "VoidPirateEntity" that represents a Void Pirate
     */
    public static final RegistryObject<EntityType<VoidPirateEntity>> VOID_PIRATE = ENTITY_TYPES.register("void_pirate",
            () -> EntityType.Builder.of(VoidPirateEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).build("void_pirate"));

    /**
     * A RegistryObject of type "EntityType" of type "EnderDragonHatchlingEntity" that represents an Ender Dragon Hatchling
     */
    public static final RegistryObject<EntityType<EnderDragonHatchlingEntity>> ENDER_DRAGON_HATCHLING = ENTITY_TYPES.register("ender_dragon_hatchling",
            () -> EntityType.Builder.of(EnderDragonHatchlingEntity::new, MobCategory.MONSTER)
                    .sized(1.5F, 1.5F).build("ender_dragon_hatchling"));


    /**
     * A RegistryObject of type "EntityType" of type "EvolvedEndermiteEntity" that represents an Evolved Endermite
     */
    public static final RegistryObject<EntityType<EvolvedEndermiteEntity>> EVOLVED_ENDERMITE_ENTITY = ENTITY_TYPES.register("evolved_endermite",
            () -> EntityType.Builder.of(EvolvedEndermiteEntity::new, MobCategory.MONSTER)
                    .sized(1.75F, 1.75F).build("evolved_endermite"));

    /**
     * Registers all entities under the DeferredRegister for entities of this mod
     * @param iEventBus The EventBus of this mod
     */
    public static void register(IEventBus iEventBus)
    {
        ENTITY_TYPES.register(iEventBus);
    }
}
