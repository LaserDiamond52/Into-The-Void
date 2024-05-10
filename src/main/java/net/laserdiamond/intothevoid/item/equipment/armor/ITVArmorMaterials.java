package net.laserdiamond.intothevoid.item.equipment.armor;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * Enum that contains all ArmorMaterial tiers for "Into The Void" mod
 */
public enum ITVArmorMaterials implements ArmorMaterial {

    LONSDALEITE("lonsdaleite", 40,new int[]{4,9,7,4},15, SoundEvents.ARMOR_EQUIP_DIAMOND, 4F, 0.05F, () -> Ingredient.of(ITVItems.REFINED_LONSDALEITE.get())),
    ENDERITE("enderite", 40, new int[]{5,10,8,5}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 1.5F, 0.2F, () -> Ingredient.of(ITVItems.ENDERITE.get())),
    DRAGONBORNE("dragonborne", 40, new int[]{6,11,9,6}, 18, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.2F, () -> Ingredient.of(ITVItems.DRAGON_HIDE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmount;
    private final int enchantValue;
    private final SoundEvent equipSound;
    private final float tougness;
    private final float kbRes;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    ITVArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmount, int enchantValue, SoundEvent equipSound, float tougness, float kbRes, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmount = protectionAmount;
        this.enchantValue = enchantValue;
        this.equipSound = equipSound;
        this.tougness = tougness;
        this.kbRes = kbRes;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmount[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return IntoTheVoid.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.tougness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.kbRes;
    }
}
