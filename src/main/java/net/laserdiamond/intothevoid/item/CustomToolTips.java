package net.laserdiamond.intothevoid.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public interface CustomToolTips {

    /**
     * Defines whether the default tooltips on the item should be hidden
     * @return True if default tooltips should be hidden, false if not
     */
    boolean hideDefaultToolTips();

    /**
     * Defines the custom tooltip that should be applied to the item
     * @param player The player owning the item
     * @return A list of Components that make up the tooltip
     */
    List<Component> toolTip(Player player);

    /**
     * Applies the tooltip to the item
     * @param event ItemTooltipEvent
     */
    default void applyToolTips(final ItemTooltipEvent event)
    {
        ItemStack itemStack = event.getItemStack();
        List<Component> toolTip = event.getToolTip();
        Player player = event.getEntity();

        if (itemStack.getItem() == this)
        {
            if (hideDefaultToolTips())
            {
                for (ItemStack.TooltipPart tooltipPart : ItemStack.TooltipPart.values())
                {
                    itemStack.hideTooltipPart(tooltipPart);
                }
            }
            toolTip.addAll(toolTip(player));
        }
    }
}
