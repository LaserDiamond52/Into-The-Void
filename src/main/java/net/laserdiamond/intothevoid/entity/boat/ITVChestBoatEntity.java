package net.laserdiamond.intothevoid.entity.boat;

import net.laserdiamond.intothevoid.entity.ITVEntities;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/**
 * Class that represents a chest boat entity of this mod
 */
public class ITVChestBoatEntity extends ChestBoat implements BoatVariants {

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ITVChestBoatEntity.class, EntityDataSerializers.INT);

    public ITVChestBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ITVChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ITVEntities.PURPUR_WOOD_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    /**
     * The item to drop when the boat is destroyed
     * @return The item to drop as an Item object
     */
    @Override
    public Item getDropItem() {
        return switch (getITVVariant())
        {
            case PURPUR -> ITVItems.PURPUR_WOOD_CHEST_BOAT.get();
        };
    }

    @Override
    public void setVariant(ITVBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    @Override
    public ITVBoatEntity.Type getITVVariant()
    {
        return ITVBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    /**
     * Defines the Synched Data for the Boat Entity
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, ITVBoatEntity.Type.PURPUR.ordinal());
    }

    /**
     * Adds additional save data to the Boat Entity
     * @param pCompound The CompoundTag
     */
    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getITVVariant().getSerializedName());
    }

    /**
     * Reads any additional saved data from the Boat Entity
     * @param pCompound The CompoundTag
     */
    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8))
        {
            this.setVariant(ITVBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

}
