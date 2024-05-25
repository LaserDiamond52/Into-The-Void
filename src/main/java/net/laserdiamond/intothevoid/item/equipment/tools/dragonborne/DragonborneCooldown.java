package net.laserdiamond.intothevoid.item.equipment.tools.dragonborne;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Cooldown for Dragonborne Sword ability
 */
public class DragonborneCooldown {

    private static HashMap<UUID, Double> cooldown;

    /**
     * Sets up the cooldown
     */
    public static void setupCooldown()
    {
        cooldown = new HashMap<>();
    }

    /**
     * Sets the cooldown for the player
     * @param player The player receiving the cooldown
     * @param seconds The cooldown duration in seconds
     */
    protected static void setCooldown(Player player, int seconds)
    {
        double cd = System.currentTimeMillis() + (seconds * 1000);
        cooldown.put(player.getUUID(), cd);
    }

    /**
     * Checks if the player is still on cooldown. If the value stored in the HashMap is null or less than/equal to System.currentTimeMillis, then the player is no longer on cooldown.
     * @param player The player to check the cooldown
     * @return True if the player is not on cooldown, false if otherwise
     */
    protected static boolean checkCooldown(Player player)
    {
        if (!cooldown.containsKey(player.getUUID()) || cooldown.get(player.getUUID()) <= System.currentTimeMillis())
        {
            return true;
        }
        return false;
    }

    /**
     * Gets the remaining cooldown duration in seconds
     * @param player The player to check the cooldown of
     * @return The duration in seconds left on the cooldown as a double
     */
    protected static double getCooldown(Player player)
    {
        double lastCooldown = cooldown.get(player.getUUID());
        return (lastCooldown - System.currentTimeMillis()) / 1000;
    }
}
