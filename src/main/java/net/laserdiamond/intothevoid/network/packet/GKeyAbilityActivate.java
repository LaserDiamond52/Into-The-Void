package net.laserdiamond.intothevoid.network.packet;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.GKeyAbility;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
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

            for (GKeyAbility gKeyAbility : IntoTheVoid.G_KEY_ABILITIES)
            {
                gKeyAbility.onKeyPress(context);
            }
        });
        return true;
    }
}
