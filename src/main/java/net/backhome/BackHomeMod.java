package net.backhome;

import net.backhome.effect.ModEffects;
import net.backhome.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

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
		ModPotions.registerPotionRecipes();

		LOGGER.info("Mod initialized, new item added :)");
	}

}