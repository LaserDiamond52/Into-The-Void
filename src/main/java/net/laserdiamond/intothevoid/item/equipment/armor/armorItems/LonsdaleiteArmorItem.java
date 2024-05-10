package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.CustomToolTips;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorCrafting;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class that represents a Lonsdaleite Armor Item. Any properties that Lonsdaleite armor should have should be done through here.
 */
public final class LonsdaleiteArmorItem extends ITVArmorItem implements ArmorCrafting {

    public LonsdaleiteArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.LONSDALEITE, pType, pProperties);
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }

    @Override
    protected double[] speedAmount() {
        return new double[]{0.025,0.025,0.025,0.025};
    }

    @Override
    public List<ItemLike> materials() {
        List<ItemLike> materials = new ArrayList<>();
        for (ItemStack material : this.getMaterial().getRepairIngredient().getItems())
        {
            materials.add(material.getItem());
        }
        return materials;
    }
}
