package net.element.elementsmod.item;

import net.element.elementsmod.ElementsMod;
import net.element.elementsmod.item.custom.AspectOfTheEndItem;
import net.element.elementsmod.item.custom.ModToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems {


    public static final Item ASPECT_OF_THE_END = registerItem("aspect_of_the_end",
            new AspectOfTheEndItem(ModToolMaterials.ENDER, 1, -2.3F, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)){
                @Override
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    tooltip.add(Text.translatable("item.elementsmod.aspect_of_the_end_tooltip"));
                }
            }
    );

    public static final Item ENDER_INGOT = registerItem("ender_ingot",
            new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ElementsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ElementsMod.LOGGER.debug("Registering Mod Items For " + ElementsMod.MOD_ID);
    }
}
