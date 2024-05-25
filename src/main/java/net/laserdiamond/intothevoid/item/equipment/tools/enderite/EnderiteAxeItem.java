package net.laserdiamond.intothevoid.item.equipment.tools.enderite;

import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVSimpleAxeItem;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVToolTiers;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolSmithing;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an Enderite Axe item
 */
public final class EnderiteAxeItem extends ITVSimpleAxeItem implements ToolSmithing {
    public EnderiteAxeItem(float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(ITVToolTiers.ENDERITE, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public List<ItemLike> materials() {
        List<ItemLike> materials = new ArrayList<>();
        materials.add(ITVItems.ENDERITE.get());
        return materials;
    }

    @Override
    public ItemLike template() {
        return ITVItems.ENDERITE_SMITHING_TEMPLATE.get();
    }

    @Override
    public ItemLike toolItem() {
        return Items.DIAMOND_AXE;
    }
}
