package net.laserdiamond.intothevoid.network;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.network.packet.GKeyAbilityActivate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {

    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id()
    {
        return packetId++;
    }

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


    public static <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToAllClients(MSG message)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
