package net.laserdiamond.intothevoid.item.equipment.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.laserdiamond.intothevoid.item.CustomToolTips;
import net.laserdiamond.intothevoid.item.ItemTaggable;
import net.laserdiamond.intothevoid.item.equipment.ItemAttributeUUIDs;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
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
public abstract class ITVArmorItem extends ArmorItem implements ItemTaggable {

    public List<MobEffectInstance> effects;
    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    private List<TagKey<Item>> itemTags;

    public ITVArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
        this.effects = new ArrayList<>();
        this.itemTags = new ArrayList<>();

        int slot = EQUIPMENT_SLOT_INTEGER_HASH_MAP.get(pType.getSlot());
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.builder();
        UUID uuid = ItemAttributeUUIDs.ARMOR_UUIDS[slot];
        attributeBuilder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.getDefense(), AttributeModifier.Operation.ADDITION));
        attributeBuilder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", this.getToughness(), AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0.0F) {
            attributeBuilder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        if (healthAmount()[slot] != 0.0)
        {
            attributeBuilder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Armor health", healthAmount()[slot], AttributeModifier.Operation.ADDITION));
        }
        if (meleeDamageAmount()[slot] != 0.0)
        {
            attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Armor melee damage", meleeDamageAmount()[slot], AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (speedAmount()[slot] != 0)
        {
            attributeBuilder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor speed", speedAmount()[slot], AttributeModifier.Operation.MULTIPLY_BASE));
        }

        this.defaultModifiers = attributeBuilder.build();
    }

    public ITVArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, List<TagKey<Item>> itemTags)
    {
        this(pMaterial, pType, pProperties);
        this.effects = new ArrayList<>();
        this.itemTags = itemTags;
    }

    /**
     * Gets the default attribute modifiers for the armor piece
     * @param pEquipmentSlot The equipment slot of the armor piece
     * @return The default attribute modifiers for the armor piece as a Multimap
     */
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot); // Copy what the parenting class does, except with OUR defaultModifiers Multimap
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
        return effects;
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

    /**
     * Appends tooltips for the armor item
     * @param pStack The ItemStack representing the item
     * @param pLevel The Level
     * @param pTooltipComponents The tooltips to add
     * @param pIsAdvanced Whether "Advanced Tooltips" is enabled
     */
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

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public List<TagKey<Item>> getItemTags() {
        return itemTags;
    }
}
