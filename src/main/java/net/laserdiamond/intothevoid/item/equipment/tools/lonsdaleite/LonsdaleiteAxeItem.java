package net.laserdiamond.intothevoid.item.equipment.tools.lonsdaleite;

import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVSimpleAxeItem;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVToolTiers;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolCrafting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

public final class LonsdaleiteAxeItem extends ITVSimpleAxeItem implements ToolCrafting {
    public LonsdaleiteAxeItem(float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(ITVToolTiers.LONSDALEITE, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public List<ItemLike> materials() {
        List<ItemLike> materials = new ArrayList<>();

        for (ItemStack material : this.getTier().getRepairIngredient().getItems())
        {
            materials.add(material.getItem());
        }
        return materials;
    }

    @Override
    public ItemLike stickMaterial() {
        return ITVItems.IRON_HANDLE.get();
    }
}
