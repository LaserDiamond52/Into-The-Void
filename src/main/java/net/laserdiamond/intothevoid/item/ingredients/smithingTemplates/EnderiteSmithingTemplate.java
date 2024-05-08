package net.laserdiamond.intothevoid.item.ingredients.smithingTemplates;

import net.laserdiamond.intothevoid.item.CustomToolTips;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.ingredients.ITVSmithingTemplateItem;
import net.laserdiamond.intothevoid.util.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public final class EnderiteSmithingTemplate extends ITVSmithingTemplateItem implements CustomToolTips {
    public EnderiteSmithingTemplate(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean hideDefaultToolTips() {
        return false;
    }

    @Override
    public List<Component> toolTip(Player player) {
        List<Component> toolTip = new ArrayList<>();
        toolTip.add(Component.literal(TextColor.GRAY + "Enderite Upgrade"));
        toolTip.add(Component.literal(" "));
        toolTip.add(Component.literal(TextColor.GRAY + "Applies to:"));
        toolTip.add(Component.literal(TextColor.BLUE + " Diamond Equipment"));
        toolTip.add(Component.literal(TextColor.GRAY + "Ingredients:"));
        toolTip.add(Component.literal(TextColor.BLUE + " Enderite"));
        return toolTip;
    }

    @SubscribeEvent
    @Override
    public void applyToolTips(final ItemTooltipEvent event) {
        CustomToolTips.super.applyToolTips(event);
    }

}
