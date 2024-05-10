package net.laserdiamond.intothevoid.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public interface CustomToolTips {

    /**
     * Defines whether the default tooltips on the item should be hidden
     * @return True if default tooltips should be hidden, false if not
     */
    boolean hideDefaultToolTips();

    /**
     * Defines the custom tooltip that should be applied to the item
     * @return A list of Components that make up the tooltip
     */
    List<Component> toolTip();


}
