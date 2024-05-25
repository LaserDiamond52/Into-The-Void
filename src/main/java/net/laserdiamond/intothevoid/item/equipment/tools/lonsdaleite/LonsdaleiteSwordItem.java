package net.laserdiamond.intothevoid.item.equipment.tools.lonsdaleite;

import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVSimpleSwordItem;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVToolTiers;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolCrafting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Lonsdaleite Sword item
 */
public final class LonsdaleiteSwordItem extends ITVSimpleSwordItem implements ToolCrafting {
    public LonsdaleiteSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(ITVToolTiers.LONSDALEITE, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        MinecraftForge.EVENT_BUS.register(this);
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

    /**
     * When a player hits a living entity with this sword, and they are capable of performing a sweep attack (meaning the sword cooldown is up), any armor that the living entity is wearing will take an additional 2 damage.
     * @param event AttackEntityEvent
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onHit(AttackEntityEvent event)
    {
        final Player damager = event.getEntity();
        final Entity target = event.getTarget();

        final int durabilityDamage = 2;

        if (this.canPerformAction(damager.getMainHandItem(), ToolActions.SWORD_SWEEP))
        {
            damager.playSound(SoundEvents.ANVIL_HIT);
            if (target instanceof Player playerTarget)
            {
                for (int i = 0; i < 3; i++)
                {
                    ItemStack armorStack = playerTarget.getInventory().getArmor(i);
                    if (armorStack.isEmpty())
                    {
                        continue;
                    }
                    int currentDamage = armorStack.getDamageValue();
                    armorStack.setDamageValue(currentDamage + durabilityDamage);
                }

            } else if (target instanceof LivingEntity livingEntity)
            {
                for (ItemStack armorStack : livingEntity.getArmorSlots())
                {
                    if (armorStack.isEmpty())
                    {
                        continue;
                    }
                    int currentDamage = armorStack.getDamageValue();
                    armorStack.setDamageValue(currentDamage + durabilityDamage);
                }
            }
        }
    }
}
