package net.laserdiamond.intothevoid.network;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.network.packet.GKeyAbilityActivate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * Packet handler for this mod
 */
public class PacketHandler {

    private static SimpleChannel INSTANCE;
    private static int packetId = 0;

    /**
     * ID of the packet. Everytime this method is called, the id is incremented by 1
     * @return The ID of the packet (incremented by 1 from the previous call of this method)
     */
    private static int id()
    {
        return packetId++;
    }

    /**
     * Registers packets needed for this mod
     */
    public static void register()
    {

        INSTANCE = NetworkRegistry.ChannelBuilder.named(
                        new ResourceLocation(IntoTheVoid.MODID, "main"))
                .serverAcceptedVersions(s -> true)
                .clientAcceptedVersions(s -> true)
                .networkProtocolVersion(() -> "1.0")
                .simpleChannel();

        INSTANCE.messageBuilder(GKeyAbilityActivate.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(GKeyAbilityActivate::new)
                .encoder(GKeyAbilityActivate::toBytes)
                .consumerMainThread(GKeyAbilityActivate::handle)
                .add();

    }

    /**
     * Sends a packet to the server
     * @param message The message object to send
     * @param <MSG> A message object
     */
    public static <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    /**
     * Sends a packet to a player
     * @param message The message object to send
     * @param player The player receiving the packet
     * @param <MSG> A message object
     */
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    /**
     * Sends a packet to all clients
     * @param message The message object to send
     * @param <MSG> A message object
     */
    public static <MSG> void sendToAllClients(MSG message)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
