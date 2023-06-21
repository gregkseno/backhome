package net.backhome;

import net.backhome.effect.ModEffects;
import net.backhome.potion.ModPotions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackHomeMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
    public static final Logger LOGGER = LoggerFactory.getLogger("backhome");

	public static String MOD_ID = "backhome";

	@Override
	public void onInitialize() {

		// //Add Town Portal Scroll to ItemGroups.TOOLS
		// ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
    	// content.add(TOWN_PORTAL_SCROLL);
    	// });
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		LOGGER.info("Mod initialized, new item added :)");
	}

}