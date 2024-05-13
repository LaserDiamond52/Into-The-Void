package net.laserdiamond.intothevoid.item;

import net.minecraftforge.network.NetworkEvent;

public interface GKeyAbility {

    /**
     * Runs this method when the key to activate the ability is pressed
     * @param context NetworkEvent.Context
     */
    void onKeyPress(NetworkEvent.Context context);
}
