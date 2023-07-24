package net.backhome.potion;

import net.backhome.BackHomeMod;
import net.backhome.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;

import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static Potion TELEPORT_POTION;
    public static Potion STRONG_TELEPORT_POTION;
    
    private static Potion registerPotion(String name, int duration){
        return Registry.register(Registries.POTION, new Identifier(BackHomeMod.MOD_ID, name), 
                new Potion(new StatusEffectInstance(ModEffects.TELEPORT_EFFECT, duration, 0)));
    }
    
    public static void registerPotions(){
        TELEPORT_POTION = registerPotion("teleport_potion", 100);
        STRONG_TELEPORT_POTION = registerPotion("strong_teleport_potion", 30);
    }

    public static void registerPotionRecipes(){
        BrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, Items.ENDER_PEARL, ModPotions.TELEPORT_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.TELEPORT_POTION, Items.REDSTONE, ModPotions.STRONG_TELEPORT_POTION);
    }
}
