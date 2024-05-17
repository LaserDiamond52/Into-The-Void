package net.laserdiamond.intothevoid.entity;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.boat.ITVBoatEntity;
import net.laserdiamond.intothevoid.entity.boat.ITVChestBoatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ITVEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, IntoTheVoid.MODID);

    public static final RegistryObject<EntityType<ITVBoatEntity>> PURPUR_WOOD_BOAT = ENTITY_TYPES.register("purpur_wood_boat",
            () -> EntityType.Builder.<ITVBoatEntity>of(ITVBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("purpur_wood_boat"));

    public static final RegistryObject<EntityType<ITVChestBoatEntity>> PURPUR_WOOD_CHEST_BOAT = ENTITY_TYPES.register("purpur_wood_chest_boat",
            () -> EntityType.Builder.<ITVChestBoatEntity>of(ITVChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("purpur_wood_chest_boat"));


    public static void register(IEventBus iEventBus)
    {
        ENTITY_TYPES.register(iEventBus);
    }
}
