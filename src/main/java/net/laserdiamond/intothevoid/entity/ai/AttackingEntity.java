package net.laserdiamond.intothevoid.entity.ai;

/**
 * An interface used to set and check if the entity is attacking
 */
public interface AttackingEntity {

    /**
     * Sets if the entity is in an attacking state
     * @param attacking True if attacking, false if not
     */
    void setAttacking(boolean attacking);

    /**
     * Returns if the entity is attacking
     * @return True if attacking, false if not
     */
    boolean isAttacking();
}
