package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorSmithing;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class that represents an Enderite Armor Item. Any properties that Enderite armor should have should be done through here
 */
public final class EnderiteArmorItem extends ITVArmorItem implements ArmorSmithing {

    public EnderiteArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.ENDERITE, pType, pProperties);
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
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

    @Override
    public ItemLike template() {
        return ITVItems.ENDERITE_SMITHING_TEMPLATE.get();
    }

    @Override
    public ItemLike armorPiece(EquipmentSlot slot) {
        switch (slot)
        {
            case HEAD ->
            {
                return Items.DIAMOND_HELMET;
            }
            case CHEST ->
            {
                return Items.DIAMOND_CHESTPLATE;
            }
            case LEGS ->
            {
                return Items.DIAMOND_LEGGINGS;
            }
            case FEET ->
            {
                return Items.DIAMOND_BOOTS;
            }
        }
        return Items.AIR;
    }
}
