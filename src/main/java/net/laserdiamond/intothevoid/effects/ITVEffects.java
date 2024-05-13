package net.laserdiamond.intothevoid.effects;

import net.laserdiamond.intothevoid.IntoTheVoid;
import net.laserdiamond.intothevoid.effects.harmfulEffects.AbsoluteDamageEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ITVEffects {

    /**
     * A DeferredRegister object of type "MobEffect" that is used to register potion/mob effects
     */
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, IntoTheVoid.MODID);

    /**
     * RegistryObject of type "MobEffect" that represents the Absolute Damage effect
     * <p>Absolute Damage is similar to instant damage, in that mobs will be damaged when it is applied to them. However, undead mobs are not damaged by the instant damage effect (instant health damages them instead).</p>
     * <p>Absolute Damage solves that problem, and will damage ANY mob that is victim to it</p>
     * <p>This effect is primarily used for the Dragonborne fireball so that the area effect cloud from it can damage any mob</p>
     */
    public static final RegistryObject<MobEffect> ABSOLUTE_DAMAGE = EFFECTS.register("absolute_damage", () -> new AbsoluteDamageEffect(11101546));

    public static void register(IEventBus eventBus)
    {
        EFFECTS.register(eventBus);
    }

}
