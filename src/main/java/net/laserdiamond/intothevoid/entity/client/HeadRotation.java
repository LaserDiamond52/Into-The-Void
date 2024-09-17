package net.laserdiamond.intothevoid.entity.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import java.util.Objects;

/**
 * Interface used for head rotations
 * @param <T> The {@link Entity} type
 */
public interface HeadRotation<T extends Entity> {

    /**
     * Helps with rotation of the head for the entity
     * @param entity The entity
     * @param headYaw The yaw of the head
     * @param headPitch the pitch of the head
     */
    default void headRotation(T entity, float headYaw, float headPitch)
    {
        final ModelPart head = Objects.requireNonNull(head());

        headYaw = Mth.clamp(headYaw, -30F, 30F);
        headPitch = Mth.clamp(headPitch, -25F, 25F);

        head.xRot = headYaw * ((float) Math.PI / 180F);
        head.yRot = headPitch * ((float) Math.PI / 180F);
    }

    /**
     * The head of the model
     * @return A {@link ModelPart} representing the head of the model
     */
    ModelPart head();
}
