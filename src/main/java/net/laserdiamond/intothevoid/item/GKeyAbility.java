package net.laserdiamond.intothevoid.item;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.network.NetworkEvent;

/**
 * An interface used for functionality of the G key Ability (ability key) for items
 */
public interface GKeyAbility {

    /**
     * Runs this method on the server when the key to activate the ability is pressed
     * @param context {@link net.minecraftforge.network.NetworkEvent.Context}
     */
    void onKeyPressServer(NetworkEvent.Context context);

    /**
     * Runs this method on the client when the key to activate the ability is pressed
     * @param inputEvent {@link net.minecraftforge.client.event.InputEvent.Key}
     */
    void onKeyPressClient(InputEvent.Key inputEvent);
}
