package net.laserdiamond.intothevoid.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Class that represents a simple item of this mod
 */
public class ITVSimpleItem extends Item {
    public ITVSimpleItem(Properties pProperties) {
        super(pProperties);
    }

    /**
     * Appends the custom tooltips to the item
     * @param pStack The ItemStack
     * @param pLevel The world
     * @param pTooltipComponents The tooltip
     * @param pIsAdvanced The advanced tooltip
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if (pStack.getItem() instanceof CustomToolTips customToolTips)
        {
            if (customToolTips.hideDefaultToolTips())
            {
                for (ItemStack.TooltipPart tooltipPart : ItemStack.TooltipPart.values())
                {
                    pStack.hideTooltipPart(tooltipPart);
                }
            }
            pTooltipComponents.addAll(customToolTips.toolTip());
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
