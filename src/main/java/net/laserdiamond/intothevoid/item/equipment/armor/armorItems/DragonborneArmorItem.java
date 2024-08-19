package net.laserdiamond.intothevoid.item.equipment.armor.armorItems;

import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.armor.ArmorSmithing;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorItem;
import net.laserdiamond.intothevoid.item.equipment.armor.ITVArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

public final class DragonborneArmorItem extends ITVArmorItem implements ArmorSmithing {
    public DragonborneArmorItem(Type pType, Properties pProperties) {
        super(ITVArmorMaterials.DRAGONBORNE, pType, pProperties);
    }

    @Override
    protected double[] meleeDamageAmount() {
        return new double[]{0.05,0.05,0.05,0.05};
    }

    @Override
    public boolean simpleArmorItem() {
        return true;
    }

    @Override
    public List<MobEffectInstance> armorEffects() {
        List<MobEffectInstance> effectInstances = new ArrayList<>();
        effectInstances.add(new MobEffectInstance(MobEffects.JUMP, 100, 0, false, false, true));
        effectInstances.add(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0, false, false, true));
        return effectInstances;
    }

    @Override
    public List<ItemLike> materials() {
        return List.of(ITVItems.REFINED_END_CRYSTAL.get());
    }

    @Override
    public ItemLike template() {
        return ITVItems.DRAGONBORNE_SMITHING_TEMPLATE.get();
    }

    @Override
    public ItemLike armorPiece(EquipmentSlot slot) {
        return switch (slot)
        {
            case HEAD -> ITVItems.ENDERITE_HELMET.get();
            case CHEST -> ITVItems.ENDERITE_CHESTPLATE.get();
            case LEGS -> ITVItems.ENDERITE_LEGGINGS.get();
            case FEET -> ITVItems.ENDERITE_BOOTS.get();
            default -> Items.AIR;
        };
    }
}
