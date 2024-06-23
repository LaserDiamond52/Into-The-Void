package net.laserdiamond.intothevoid.events;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.ITVEntities;
import net.laserdiamond.intothevoid.entity.itv.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * The Event Handler for events that should be registered under the mod bus of this mod
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = IntoTheVoid.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    /**
     * Registers all the entity attributes needed for entities of this mod
     * @param event The EntityAttributeCreatingEvent
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ITVEntities.VOID_PIRATE.get(), VoidPirateEntity.createAttributes().build());
        event.put(ITVEntities.ENDER_DRAGON_HATCHLING.get(), EnderDragonHatchlingEntity.createAttributes().build());
        event.put(ITVEntities.EVOLVED_ENDERMITE_ENTITY.get(), EvolvedEndermiteEntity.createAttributes().build());
        event.put(ITVEntities.WATCHER_BOSS.get(), WatcherBossEntity.createAttributes().build());
        event.put(ITVEntities.WATCHER_MINION.get(), WatcherMinionEntity.createAttributes().build());
    }
}
