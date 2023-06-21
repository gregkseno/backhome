package net.backhome.potion;

import net.backhome.BackHomeMod;
import net.backhome.TeleportPotion;
import net.backhome.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static Potion TELEPORT_POTION;
    
    private static Potion registerPotion(String name){
        return Registry.register(Registries.POTION, new Identifier(BackHomeMod.MOD_ID, name), 
                new Potion(new StatusEffectInstance(ModEffects.TELEPORT_EFFECT, 200, 0)));
    }

    public static void registerPotions(){
        TELEPORT_POTION = registerPotion("teleport_potion");
    }
}
