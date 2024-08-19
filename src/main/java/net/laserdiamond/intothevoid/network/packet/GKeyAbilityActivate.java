package net.laserdiamond.intothevoid.network.packet;

import net.laserdiamond.intothevoid.item.GKeyAbility;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Packet used to detect the key press for abilities (Client to Server)
 */
public class GKeyAbilityActivate {

    public GKeyAbilityActivate(){}
    public GKeyAbilityActivate(FriendlyByteBuf buf) {}
    public void toBytes(FriendlyByteBuf buf) {}

    /**
     * Handles the functionality of packets sent from the client to the server. Anything that runs inside the NetworkEvent Context in this method is running server side in-game
     * @param supplier A supplier of the Network Event Context
     * @return True (no instance where it returns false right now)
     */
    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // ON THE SERVER!

            ServerPlayer player = context.getSender();
            if (player != null)
            {
                if (player.getMainHandItem().getItem() instanceof GKeyAbility gKeyAbility)
                {
                    gKeyAbility.onKeyPressServer(context);
                }
            }
        });
        return true;
    }
}
