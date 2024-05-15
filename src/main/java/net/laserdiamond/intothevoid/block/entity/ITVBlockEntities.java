package net.laserdiamond.intothevoid.block.entity;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ITVBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IntoTheVoid.MODID);

    public static final RegistryObject<BlockEntityType<ITVSignBlockEntity>> ITV_SIGN = BLOCK_ENTITIES.register("itv_sign",
            () -> BlockEntityType.Builder.of(ITVSignBlockEntity::new,
                    ITVBlocks.PURPUR_WOOD_SIGN.get(), ITVBlocks.PURPUR_WOOD_WALL_SIGN.get())
                    .build(null));

    public static final RegistryObject<BlockEntityType<ITVHangingSignBlockEntity>> ITV_HANGING_SIGN = BLOCK_ENTITIES.register("itv_hanging_sign",
            () -> BlockEntityType.Builder.of(ITVHangingSignBlockEntity::new,
                    ITVBlocks.PURPUR_WOOD_HANGING_SIGN.get(), ITVBlocks.PURPUR_WOOD_WALL_HANGING_SIGN.get())
                    .build(null));

    public static final RegistryObject<BlockEntityType<RefineryBlockEntity>> REFINERY = BLOCK_ENTITIES.register("refinery_be",
            () -> BlockEntityType.Builder.of(RefineryBlockEntity::new, ITVBlocks.REFINERY.get())
                    .build(null));


    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
