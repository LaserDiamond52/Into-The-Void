package net.laserdiamond.intothevoid.item.misc;

import net.laserdiamond.intothevoid.entity.boat.ITVBoatEntity;
import net.laserdiamond.intothevoid.entity.boat.ITVChestBoatEntity;
import net.laserdiamond.intothevoid.item.ITVSimpleItem;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * A boat item of this mod
 */
public class ITVBoatItem extends ITVSimpleItem {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);;
    private final ITVBoatEntity.Type type;
    private final boolean hasChest;

    public ITVBoatItem(boolean pHasChest, ITVBoatEntity.Type pType, Properties pProperties) {
        super(pProperties);
        this.hasChest = pHasChest;
        this.type = pType;

    }

    /**
     * Attempts to spawn a boat where the player right-clicked. Works the same as vanilla boat variants
     * @param pLevel The level/world the player is in
     * @param pPlayer The player spawning the boat
     * @param pHand The player's hand they are holding the boat item in (main/off-hand)
     * @return InteractionResultHolder of type "ItemStack"
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        HitResult hitResult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemInHand);
        } else {
            Vec3 playerViewVector = pPlayer.getViewVector(1.0F);
            double v = 5.0;
            List<Entity> entities = pLevel.getEntities(pPlayer, pPlayer.getBoundingBox().expandTowards(playerViewVector.scale(5.0)).inflate(1.0), ENTITY_PREDICATE);
            if (!entities.isEmpty()) {
                Vec3 playerEyePosition = pPlayer.getEyePosition();

                for (Entity entity : entities) {
                    AABB inflate = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (inflate.contains(playerEyePosition)) {
                        return InteractionResultHolder.pass(itemInHand);
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK)
            {
                Boat boat = this.getBoat(pLevel, hitResult);
                if (boat instanceof ITVBoatEntity itvBoatEntity)
                {
                    itvBoatEntity.setVariant(this.type);
                } else if (boat instanceof ITVChestBoatEntity itvChestBoatEntity)
                {
                    itvChestBoatEntity.setVariant(this.type);
                }
                boat.setYRot(pPlayer.getYRot());
                if (!pLevel.noCollision(boat, boat.getBoundingBox()))
                {
                    return InteractionResultHolder.fail(itemInHand);
                } else
                {
                    if (!pLevel.isClientSide)
                    {
                        pLevel.addFreshEntity(boat);
                        pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                        if (!pPlayer.getAbilities().instabuild)
                        {
                            itemInHand.shrink(1);
                        }
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemInHand, pLevel.isClientSide);
                }
            } else {
                return InteractionResultHolder.pass(itemInHand);
            }
        }
    }

    private Boat getBoat(Level pLevel, HitResult pHitResult) {
        return this.hasChest ? new ITVChestBoatEntity(pLevel, pHitResult.getLocation().x, pHitResult.getLocation().y, pHitResult.getLocation().z) : new ITVBoatEntity(pLevel, pHitResult.getLocation().x, pHitResult.getLocation().y, pHitResult.getLocation().z);
    }

}
