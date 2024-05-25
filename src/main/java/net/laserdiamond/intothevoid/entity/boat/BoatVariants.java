package net.laserdiamond.intothevoid.entity.boat;

public interface BoatVariants {

    /**
     * Sets the variant of the boat when spawned
     * @param pVariant The ITVBoatEntity Type for the boat
     */
    void setVariant(ITVBoatEntity.Type pVariant);

    /**
     * Gets the ITVBoatEntity Type of the boat
     * @return The type as an ITVBoatEntity.Type object
     */
    ITVBoatEntity.Type getITVVariant();
}
