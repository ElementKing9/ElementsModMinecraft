package net.element.elementsmod;

import net.element.elementsmod.block.ModBlocks;
import net.element.elementsmod.item.ModItemGroup;
import net.element.elementsmod.item.ModItems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementsMod implements ModInitializer {
	public static final String MOD_ID = "elementsmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ELEMENTSMODITEMGROUP).register(content -> {
			content.add(ModItems.ASPECT_OF_THE_END);
			content.add(ModItems.ENDER_INGOT);
		});
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}
