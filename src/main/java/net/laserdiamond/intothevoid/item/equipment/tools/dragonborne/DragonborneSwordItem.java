package net.laserdiamond.intothevoid.item.equipment.tools.dragonborne;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.projectiles.DragonFireballs.DragonborneFireball;
import net.laserdiamond.intothevoid.item.GKeyAbility;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVComplexSwordItem;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVToolTiers;
import net.laserdiamond.intothevoid.item.equipment.tools.ToolSmithing;
import net.laserdiamond.intothevoid.util.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;

/**
 * Class that represents the Dragonborne Sword item
 */
public final class DragonborneSwordItem extends ITVComplexSwordItem implements GKeyAbility, ToolSmithing {

    public DragonborneSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(ITVToolTiers.DRAGONBORNE, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void onKeyPressServer(NetworkEvent.Context context) {

        ServerPlayer serverPlayer = context.getSender();
        ServerLevel level = serverPlayer.serverLevel().getLevel();

        if (DragonborneCooldown.checkCooldown(serverPlayer))
        {
            level.playSound(null, serverPlayer.getOnPos(), SoundEvents.ENDER_DRAGON_SHOOT, SoundSource.PLAYERS, 100, 1);
            Vec3 playerView = serverPlayer.getLookAngle();
            double x = serverPlayer.getX();
            double eyeY = serverPlayer.getEyeY();
            double z = serverPlayer.getZ();

            DragonborneFireball dragonFireball = new DragonborneFireball(level, serverPlayer, playerView.x, playerView.y, playerView.z);
            dragonFireball.setPos(x, eyeY, z);
            level.addFreshEntity(dragonFireball);
            DragonborneCooldown.setCooldown(serverPlayer, 5);
        } else
        {
            serverPlayer.sendSystemMessage(Component.literal(TextColor.RED + "Ability is on cooldown for " + DragonborneCooldown.getCooldown(serverPlayer) + " seconds"));
        }
    }

    @Override
    public void onKeyPressClient(InputEvent.Key inputEvent) {
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
    public ItemLike toolItem() {
        return ITVItems.ENDERITE_SWORD.get();
    }
}
