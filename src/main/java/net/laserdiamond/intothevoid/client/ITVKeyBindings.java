package net.laserdiamond.intothevoid.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.laserdiamond.intothevoid.IntoTheVoid;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class ITVKeyBindings {

    public static final ITVKeyBindings INSTANCE = new ITVKeyBindings();

    private ITVKeyBindings() {}

    private static final String INTO_THE_VOID_CATEGORY = "key.categories." + IntoTheVoid.MODID;

    public final KeyMapping abilityActivate = new KeyMapping(
            "key." + IntoTheVoid.MODID + ".abilityActivate",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_G, -1),
            INTO_THE_VOID_CATEGORY);
}
