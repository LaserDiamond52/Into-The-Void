package net.laserdiamond.intothevoid.item.equipment.armor;

import com.google.common.collect.Multimap;
import net.laserdiamond.intothevoid.item.CustomToolTips;
import net.laserdiamond.intothevoid.item.equipment.ItemAttributeUUIDs;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Abstract class that represents an armor item of this mod
 */
public abstract class ITVArmorItem extends ArmorItem {

    public ITVArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    /**
     * Returns if each armor item of this armor set should be a simple armor item
     * @return true if simple, false if not
     */
    public abstract boolean simpleArmorItem();

    /**
     * The amount of health each armor piece should give the player when worn
     * @return The health values for the armor in a double array (index 0 = helmet, 1 = chestplate, etc...)
     */
    protected double[] healthAmount()
    {
        return new double[]{0,0,0,0};
    }

    /**
     * The bonus melee damage percent increase each armor piece should give the player when worn
     * @return The melee damage percent increase each armor piece grants (index 0 = helmet, 1 = chestplate, etc...)
     */
    protected double[] meleeDamageAmount()
    {
        return new double[]{0,0,0,0};
    }

    /**
     * The movement speed percent increase each armor piece should give the player when worn
     * @return The movement speed percent increase each armor piece grants (index 0 = helmet, 1 = chestplate, etc...)
     */
    protected double[] speedAmount()
    {
        return new double[]{0,0,0,0};
    }

    /**
     * A list of MobEffectInstance objects (potion effects) that the armor set should grant when fully worn
     * @return A List of MobEffectInstance objects
     */
    public List<MobEffectInstance> armorEffects()
    {
        return new ArrayList<>();
    }

    /**
     * A method that runs whenever a player is wearing armor
     * @param stack The itemStack the player is going to equip themselves with in their armor slots in the inventory
     * @param level The world the player is in
     * @param player The player equipping/unequipping armor
     */
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player)
    {
        if (!level.isClientSide())
        {
            if (!hasFullArmorOn(player))
            {
                return;
            }
            if (hasFullSetOn(player, this.getMaterial()))
            {
                for (MobEffectInstance effectInstance : armorEffects())
                {
                    boolean hasPlayerEffect = player.hasEffect(effectInstance.getEffect());

                    if (!hasPlayerEffect)
                    {
                        player.addEffect(effectInstance);
                    }
                }
            }
            if (hasFullSetOn(player, ArmorMaterials.NETHERITE))
            {
                boolean hasFireResistance = player.hasEffect(MobEffects.FIRE_RESISTANCE);

                if (!hasFireResistance)
                {
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100,0,false, false, true));
                }
            }
        }
    }

    /**
     * Checks if the player has all their armor slots occupied
     * @param player The player wearing the armor
     * @return True if all armor slots are occupied, false if not
     */
    protected boolean hasFullArmorOn(Player player)
    {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }

    /**
     * Checks if the player is wearing the full armor set of the given material type
     * @param player The player wearing the armor set
     * @return True if the player is wearing the full set, false if not
     */
    protected boolean hasFullSetOn(Player player, ArmorMaterial armorMaterial)
    {
        for (ItemStack armorStack : player.getInventory().armor)
        {
            if (!((armorStack.getItem()) instanceof ArmorItem))
            {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == armorMaterial && chestplate.getMaterial() == armorMaterial && leggings.getMaterial() == armorMaterial && boots.getMaterial() == armorMaterial;
    }

    /**
     * Maps each armor piece equipment slot to the corresponding integer value for retrieving values from to following methods:
     * <p>getHealthAmount</p>
     * <p>getMeleeDamageAmount</p>
     * <p>getSpeedAmount</p>
     */
    private static final HashMap<EquipmentSlot, Integer> EQUIPMENT_SLOT_INTEGER_HASH_MAP = new HashMap<>();
    static
    {
        EQUIPMENT_SLOT_INTEGER_HASH_MAP.put(EquipmentSlot.HEAD, 0);
        EQUIPMENT_SLOT_INTEGER_HASH_MAP.put(EquipmentSlot.CHEST, 1);
        EQUIPMENT_SLOT_INTEGER_HASH_MAP.put(EquipmentSlot.LEGS, 2);
        EQUIPMENT_SLOT_INTEGER_HASH_MAP.put(EquipmentSlot.FEET, 3);
    }

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

        if (pStack.getItem() instanceof ITVArmorItem itvArmorItem)
        {
            final EquipmentSlot armorSlot = itvArmorItem.getEquipmentSlot();
            final int slot = EQUIPMENT_SLOT_INTEGER_HASH_MAP.get(armorSlot);

            double health = itvArmorItem.healthAmount()[slot];
            double meleeDamage = itvArmorItem.meleeDamageAmount()[slot];
            double speed = itvArmorItem.speedAmount()[slot];

            final Multimap<Attribute, AttributeModifier> modifiers = itvArmorItem.getDefaultAttributeModifiers(armorSlot);

            for (Attribute attribute : modifiers.keySet())
            {
                Collection<AttributeModifier> attributeModifiers = modifiers.get(attribute);

                for (AttributeModifier attributeModifier : attributeModifiers)
                {
                    pStack.addAttributeModifier(attribute, attributeModifier, armorSlot);
                }
            }

            pStack.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(ItemAttributeUUIDs.ARMOR_HEALTH_UUIDS[slot], "health", health, AttributeModifier.Operation.ADDITION), armorSlot);
            pStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ItemAttributeUUIDs.ARMOR_HEALTH_UUIDS[slot], "meleeDamage", meleeDamage, AttributeModifier.Operation.MULTIPLY_BASE), armorSlot);
            pStack.addAttributeModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier(ItemAttributeUUIDs.ARMOR_HEALTH_UUIDS[slot], "speed", speed, AttributeModifier.Operation.MULTIPLY_BASE), armorSlot);
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
