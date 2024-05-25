package net.laserdiamond.intothevoid.entity.boat;

import net.laserdiamond.intothevoid.block.ITVBlocks;
import net.laserdiamond.intothevoid.entity.ITVEntities;
import net.laserdiamond.intothevoid.item.ITVItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.IntFunction;

/**
 * Class that represents a boat entity of this mod
 */
public class ITVBoatEntity extends Boat implements BoatVariants {

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ITVBoatEntity.class, EntityDataSerializers.INT);
    public ITVBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ITVBoatEntity(Level level, double pX, double pY, double pZ)
    {
        this(ITVEntities.PURPUR_WOOD_BOAT.get(), level);
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
            case PURPUR -> ITVItems.PURPUR_WOOD_BOAT.get();
        };
    }

    @Override
    public void setVariant(ITVBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    @Override
    public Type getITVVariant()
    {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    /**
     * Defines the Synched Data for the Boat Entity
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.PURPUR.ordinal());
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

    /**
     * An enum consisting of the types of boats
     */
    public enum Type implements StringRepresentable {
        PURPUR (ITVBlocks.PURPUR_PLANKS.get(), "purpur_wood");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<ITVBoatEntity.Type> CODEC = StringRepresentable.fromEnum(ITVBoatEntity.Type::values);
        private static final IntFunction<ITVBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static ITVBoatEntity.Type byId(int pId) {
            return (ITVBoatEntity.Type)BY_ID.apply(pId);
        }

        public static ITVBoatEntity.Type byName(String pName) {
            return (ITVBoatEntity.Type)CODEC.byName(pName, PURPUR);
        }
    }
}
