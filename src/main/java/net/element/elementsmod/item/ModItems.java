package net.element.elementsmod.item;

import net.element.elementsmod.ElementsMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {


    public static final Item ASPECT_OF_THE_END = registerItem("aspect_of_the_end", new Item(new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ElementsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ElementsMod.LOGGER.debug("Registering Mod Items For " + ElementsMod.MOD_ID);
    }
}
