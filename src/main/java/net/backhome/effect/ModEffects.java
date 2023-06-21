package net.backhome.effect;

import net.backhome.BackHomeMod;
import net.backhome.TeleportPotion;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static TeleportEffect TELEPORT_EFFECT;
    
    private static TeleportEffect registerEffect(String name){
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(BackHomeMod.MOD_ID, name), 
                new TeleportEffect(StatusEffectCategory.BENEFICIAL, 7558074));
    }

    public static void registerEffects(){
        TELEPORT_EFFECT = registerEffect("teleport_effect");
    }    
}
