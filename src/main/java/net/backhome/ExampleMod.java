package net.backhome;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
    public static final Logger LOGGER = LoggerFactory.getLogger("backhome");


	public static final Item TOWN_PORTAL_SCROLL = new Item(new FabricItemSettings());

	@Override
	public void onInitialize() {
	
		Registry.register(Registries.ITEM, new Identifier("backhome", "town_portal_scroll"), TOWN_PORTAL_SCROLL);
		LOGGER.info("Mod initialized, new item added :)");
	}
}