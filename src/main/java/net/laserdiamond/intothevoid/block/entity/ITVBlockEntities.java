package net.laserdiamond.intothevoid.block.entity;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * The block entities of this mod. Block Entities are blocks that have user interfaces/GUIs when interacted with, or other advanced features used by the client
 */
public class ITVBlockEntities {

    /**
     * A DeferredRegister of type "BlockEntityType" of an unknown type used to register all block entities of this mod
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IntoTheVoid.MODID);

    /**
     * A RegistryObject of type "BlockEntityType" of type "ITVSignBlockEntity" that represents a Sign Block Entity of this mod
     */
    public static final RegistryObject<BlockEntityType<ITVSignBlockEntity>> ITV_SIGN = BLOCK_ENTITIES.register("itv_sign",
            () -> BlockEntityType.Builder.of(ITVSignBlockEntity::new,
                    ITVBlocks.PURPUR_WOOD_SIGN.get(), ITVBlocks.PURPUR_WOOD_WALL_SIGN.get())
                    .build(null));

    /**
     * A RegistryObject of type "BlockEntityType" of type "ITVHangingSignBlockEntity" that represents a Hanging Sign Block Entity of this mod
     */
    public static final RegistryObject<BlockEntityType<ITVHangingSignBlockEntity>> ITV_HANGING_SIGN = BLOCK_ENTITIES.register("itv_hanging_sign",
            () -> BlockEntityType.Builder.of(ITVHangingSignBlockEntity::new,
                    ITVBlocks.PURPUR_WOOD_HANGING_SIGN.get(), ITVBlocks.PURPUR_WOOD_WALL_HANGING_SIGN.get())
                    .build(null));

    /**
     * A RegistryBlock of type "BlockEntityType" of type "RefineryBlockEntity" that represents a Refinery block
     */
    public static final RegistryObject<BlockEntityType<RefineryBlockEntity>> REFINERY = BLOCK_ENTITIES.register("refinery_be",
            () -> BlockEntityType.Builder.of(RefineryBlockEntity::new, ITVBlocks.REFINERY.get())
                    .build(null));


    /**
     * Registers all Block Entities
     * @param eventBus The Event Bus of this mod
     */
    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
