package net.laserdiamond.intothevoid.item.equipment;

import java.util.UUID;

/**
 * Contains UUIDs for item attribute modifiers. UUIDs are needed for each attribute modifier to ensure that they are applied correctly
 */
public class ItemAttributeUUIDs {

    /**
     * Health attribute modifier UUIDs for armor pieces
     */
    @Deprecated
    public static final UUID[] ARMOR_HEALTH_UUIDS = new UUID[]
            {
                    UUID.fromString("723efadf-2813-4395-8d86-b8ac29f6db4e"),
                    UUID.fromString("5354cc13-05ff-456c-bc88-1580dfa106a0"),
                    UUID.fromString("df4bc350-2d13-4d9c-ae0b-bac331f50b44"),
                    UUID.fromString("fe42c411-af7f-4395-92a3-4f9ef96642ae")
            };

    /**
     * Melee damage attribute modifier UUIDs for armor pieces
     */
    @Deprecated
    public static final UUID[] MELEE_DAMAGE_UUIDS = new UUID[]
            {
                    UUID.fromString("cd9d8e20-05c7-4f4e-a59b-3b5f059a2972"),
                    UUID.fromString("557fb185-d5fb-424c-b875-02f6088d695c"),
                    UUID.fromString("5b1e6d70-3dcf-4828-b5da-44aad9b13b33"),
                    UUID.fromString("534afbe1-70b0-4ceb-993f-7d564bf6fc96")
            };

    /**
     * Speed attribute modifier UUIDs for armor pieces
     */
    @Deprecated
    public static final UUID[] ARMOR_SPEED_UUIDS = new UUID[]
            {
                    UUID.fromString("d98fd30d-6c3f-4897-b272-81bada6cf4f0"),
                    UUID.fromString("fe42963e-f75e-4080-a01a-8fe58df06a14"),
                    UUID.fromString("424bcf13-1d73-4ea8-86ef-160cdded141e"),
                    UUID.fromString("1049018c-dbd5-4124-800b-8c0bf256a39c")
            };


    /**
     * UUIDS for armor pieces
     */
    public static final UUID[] ARMOR_UUIDS = new UUID[]
            {
                    UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
                    UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
                    UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
                    UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")
            };
}
