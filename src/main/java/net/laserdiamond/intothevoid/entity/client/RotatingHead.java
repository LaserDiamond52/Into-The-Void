package net.laserdiamond.intothevoid.entity.client;

/**
 * Interface used for head rotations
 */
public interface RotatingHead {

    /**
     * Helps with rotation of the head for the entity
     * @param headYaw The yaw of the head
     * @param headPitch the pitch of the head
     */
    void headRotation(float headYaw, float headPitch);
}
