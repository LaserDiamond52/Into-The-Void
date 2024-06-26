package net.laserdiamond.intothevoid.item.ingredients.smithingTemplates;

import net.laserdiamond.intothevoid.item.CustomToolTips;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.util.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Dragonborne Smithing Template item
 */
public final class DragonborneSmithingTemplate extends ITVSmithingTemplateItem implements CustomToolTips {
    public DragonborneSmithingTemplate(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemLike materialItem() {
        return ITVItems.DRAGON_HIDE.get();
    }

    @Override
    public ItemLike mineralItem() {
        return ITVItems.ENDERITE.get();
    }

    @Override
    public boolean hideDefaultToolTips() {
        return false;
    }

    @Override
    public List<Component> toolTip() {
        List<Component> toolTip = new ArrayList<>();
        toolTip.add(Component.literal(TextColor.GRAY + "Dragonborne Upgrade"));
        toolTip.add(Component.literal(" "));
        toolTip.add(Component.literal(TextColor.GRAY + "Applies to:"));
        toolTip.add(Component.literal(TextColor.BLUE + " Enderite Equipment"));
        toolTip.add(Component.literal(TextColor.GRAY + "Ingredients:"));
        toolTip.add(Component.literal(TextColor.BLUE + " Refined End Crystal"));
        return toolTip;
    }

}
