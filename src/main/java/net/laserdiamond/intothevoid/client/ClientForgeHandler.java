package net.laserdiamond.intothevoid.client;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.block.entity.ITVBlockEntities;
import net.laserdiamond.intothevoid.item.GKeyAbility;
import net.laserdiamond.intothevoid.network.PacketHandler;
import net.laserdiamond.intothevoid.network.packet.GKeyAbilityActivate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

/**
 * The Client Handler for events that should be registered under the Forge Bus of this mod
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = IntoTheVoid.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {

    /**
     * Method is run when a key input from the client is detected. If a key with a key binding is pressed, a packet is sent from the client to the server indicating a key being pressed
     * @param event {@link net.minecraftforge.client.event.InputEvent.Key}
     */
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event)
    {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer clientPlayer = minecraft.player;
        if (ITVKeyBindings.INSTANCE.abilityActivate.consumeClick()) // ON CLIENT
        {
            if (clientPlayer != null)
            {
                if (clientPlayer.getMainHandItem().getItem() instanceof GKeyAbility gKeyAbility)
                {
                    gKeyAbility.onKeyPressClient(event);
                    PacketHandler.sendToServer(new GKeyAbilityActivate()); // Send packet from client to server when ability keybinding is pressed
                }
            }
        }
    }
}
