package net.laserdiamond.intothevoid.item.equipment.tools.dragonborne;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.entity.projectiles.DragonborneFireball;
import net.laserdiamond.intothevoid.item.GKeyAbility;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVComplexSwordItem;
import net.laserdiamond.intothevoid.item.equipment.tools.ITVToolTiers;
import net.laserdiamond.intothevoid.util.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

public final class DragonborneSwordItem extends ITVComplexSwordItem implements GKeyAbility {
    public DragonborneSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(ITVToolTiers.DRAGONBORNE, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        IntoTheVoid.G_KEY_ABILITIES.add(this);
    }

    @Override
    public void onKeyPress(NetworkEvent.Context context) {

        ServerPlayer serverPlayer = context.getSender();
        ServerLevel level = serverPlayer.serverLevel().getLevel();

        if (serverPlayer.getMainHandItem().getItem() instanceof DragonborneSwordItem)
        {
            if (DragonborneCooldown.checkCooldown(serverPlayer))
            {
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
    }
}
