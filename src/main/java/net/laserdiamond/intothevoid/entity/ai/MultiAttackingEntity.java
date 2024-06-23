package net.laserdiamond.intothevoid.entity.ai;

/**
 * An interface used to set and check multiple different attacks for an entity
 */
public interface MultiAttackingEntity {

    /**
     * Sets the entity into an attacking state
     * @param index Which attack to set
     * @param attacking True if attacking, false otherwise
     */
    void setAttacking(int index, boolean attacking);

    /**
     * Checks if the entity is in an attacking state
     * @param index Which attack to check for
     * @return True if attacking, false otherwise
     */
    boolean isAttacking(int index);
}
