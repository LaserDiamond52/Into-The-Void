package net.laserdiamond.intothevoid.item.equipment.tools.enderite;

import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVSimpleHoeItem;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVToolTiers;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolSmithing;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

public final class EnderiteHoeItem extends ITVSimpleHoeItem implements ToolSmithing {
    public EnderiteHoeItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
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
        return Items.DIAMOND_HOE;
    }
}
