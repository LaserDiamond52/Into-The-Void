package net.laserdiamond.intothevoid.item;

import net.minecraftforge.network.NetworkEvent;

/**
 * An interface used for functionality of the G key Ability (ability key)
 */
public interface GKeyAbility {

    /**
     * Runs this method when the key to activate the ability is pressed
     * @param context NetworkEvent.Context
     */
    void onKeyPress(NetworkEvent.Context context);
}
