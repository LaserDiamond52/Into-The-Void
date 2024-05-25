package net.laserdiamond.intothevoid.item.ingredients.smithingTemplates;

import net.laserdiamond.intothevoid.item.CustomToolTips;
import net.laserdiamond.intothevoid.util.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an Enderite Smithing template
 */
public final class EnderiteSmithingTemplate extends ITVSmithingTemplateItem implements CustomToolTips {

    public EnderiteSmithingTemplate(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemLike materialItem() {
        return Blocks.END_STONE.asItem();
    }

    @Override
    public ItemLike mineralItem() {
        return Items.DIAMOND;
    }

    @Override
    public boolean hideDefaultToolTips() {
        return false;
    }

    @Override
    public List<Component> toolTip() {
        List<Component> toolTip = new ArrayList<>();
        toolTip.add(Component.literal(TextColor.GRAY + "Enderite Upgrade"));
        toolTip.add(Component.literal(" "));
        toolTip.add(Component.literal(TextColor.GRAY + "Applies to:"));
        toolTip.add(Component.literal(TextColor.BLUE + " Diamond Equipment"));
        toolTip.add(Component.literal(TextColor.GRAY + "Ingredients:"));
        toolTip.add(Component.literal(TextColor.BLUE + " Enderite"));
        return toolTip;
    }
}
