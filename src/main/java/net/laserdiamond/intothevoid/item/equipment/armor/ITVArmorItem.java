package net.laserdiamond.intothevoid.item.equipment.armor;

import com.google.common.collect.Multimap;
import net.laserdiamond.intothevoid.item.equipment.ItemAttributeUUIDs;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;

/**
 * Abstract class that represents an armor item of this mod
 */
public abstract class ITVArmorItem extends ArmorItem {

    public ITVArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Returns if each armor item of this armor set should be a simple armor item
     * @return true if simple, false if not
     */
    public abstract boolean simpleArmorItem();

    protected double[] healthAmount()
    {
        return new double[]{0,0,0,0};
    }
    protected double[] meleeDamageAmount()
    {
        return new double[]{0,0,0,0};
    }
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
            if (hasFullSetOn(player))
            {
                for (MobEffectInstance effectInstance : armorEffects())
                {
                    boolean hasPlayerEffect = player.hasEffect(effectInstance.getEffect());

                    if (!hasPlayerEffect)
                    {
                        player.addEffect(new MobEffectInstance(effectInstance));
                    }
                }
            }
        }
    }

    /**
     * Checks if the player is wearing the full armor set of the given material type
     * @param player The player wearing the armor set
     * @return True if the player is wearing the full set, false if not
     */
    protected boolean hasFullSetOn(Player player)
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

        return helmet.getMaterial() == this.getMaterial() && chestplate.getMaterial() == this.getMaterial() && leggings.getMaterial() == this.getMaterial() && boots.getMaterial() == this.getMaterial();
    }

    /**
     * The tooltips that should be present on the armor pieces
     * @return a List of String objects representing the tooltips of the armor
     */
    protected List<String> tooltips()
    {
        return new ArrayList<>();
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

    /**
     * Applies the additional attribute modifiers to the armor piece
     * @param event ItemAttributeModifierEvent
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void applyAttributes(final ItemAttributeModifierEvent event)
    {
        ItemStack itemStack = event.getItemStack();

        if (itemStack.getItem() instanceof ITVArmorItem itvArmorItem)
        {
            final EquipmentSlot armorSlot = itvArmorItem.getEquipmentSlot();
            final int slot = EQUIPMENT_SLOT_INTEGER_HASH_MAP.get(armorSlot);

            double health = itvArmorItem.healthAmount()[slot];
            double meleeDamage = itvArmorItem.meleeDamageAmount()[slot];
            double speed = itvArmorItem.speedAmount()[slot];

            final Multimap<Attribute, AttributeModifier> origModifiers = itvArmorItem.getDefaultAttributeModifiers(armorSlot);

            for (Attribute attribute : origModifiers.keySet())
            {
                Collection<AttributeModifier> modifiers = origModifiers.get(attribute);

                for (AttributeModifier attributeModifier : modifiers)
                {
                    itemStack.addAttributeModifier(attribute, attributeModifier, armorSlot);
                }
            }

            UUID healthUUID = ItemAttributeUUIDs.ARMOR_HEALTH_UUIDS[slot];
            UUID meleeDamageUUID = ItemAttributeUUIDs.MELEE_DAMAGE_UUIDS[slot];
            UUID speedUUID = ItemAttributeUUIDs.ARMOR_SPEED_UUIDS[slot];

            itemStack.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(healthUUID, "health", health, AttributeModifier.Operation.ADDITION), armorSlot);
            itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(meleeDamageUUID, "melee_damage", meleeDamage, AttributeModifier.Operation.MULTIPLY_BASE), armorSlot);
            itemStack.addAttributeModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier(speedUUID, "speed", speed, AttributeModifier.Operation.MULTIPLY_BASE), armorSlot);

        }
    }
}
