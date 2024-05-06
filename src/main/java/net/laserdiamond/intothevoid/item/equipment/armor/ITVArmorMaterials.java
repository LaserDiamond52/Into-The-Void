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

    LONSDALEITE("lonsdaleite", 40, new double[]{0,0,0,0},new int[]{4,9,7,4}, new double[]{0,0,0,0}, new double[]{0.025, 0.025, 0.025, 0.025},15, SoundEvents.ARMOR_EQUIP_DIAMOND, 4F, 0.05F, () -> Ingredient.of(ITVItems.REFINED_LONSDALEITE.get())),
    ENDERITE("enderite", 40, new double[]{0,0,0,0}, new int[]{5,10,8,5}, new double[]{0,0,0,0}, new double[]{0,0,0,0}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 1.5F, 0.2F, () -> Ingredient.of(ITVItems.ENDERITE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final double[] healthAmount;
    private final int[] protectionAmount;
    private final double[] meleeDamageAmount;
    private final double[] speedAmount;
    private final int enchantValue;
    private final SoundEvent equipSound;
    private final float tougness;
    private final float kbRes;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    ITVArmorMaterials(String name, int durabilityMultiplier, double[] healthAmount, int[] protectionAmount, double[] meleeDamageAmount, double[] speedAmount, int enchantValue, SoundEvent equipSound, float tougness, float kbRes, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.healthAmount = healthAmount;
        this.protectionAmount = protectionAmount;
        this.meleeDamageAmount = meleeDamageAmount;
        this.speedAmount = speedAmount;
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

    public double getHealthAmount(int index) {
        try {
            return this.healthAmount[index];
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ERROR TRYING TO GET INDEX " + index + " FROM HEALTH AMOUNT ARRAY (SIZE: " + healthAmount.length + ")");
            return 0;
        }
    }

    public double getMeleeDamageAmount(int index) {
        try {
            return this.meleeDamageAmount[index];
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ERROR TRYING TO GET INDEX " + index + " FROM MELEE DAMAGE AMOUNT ARRAY (SIZE: " + meleeDamageAmount.length + ")");
            return 0;
        }

    }

    public double getSpeedAmount(int index) {
        try {
            return this.speedAmount[index];
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ERROR TRYING TO GET INDEX " + index + " FROM SPEED AMOUNT ARRAY (SIZE: " + speedAmount.length + ")");
            return 0;
        }
    }
}
