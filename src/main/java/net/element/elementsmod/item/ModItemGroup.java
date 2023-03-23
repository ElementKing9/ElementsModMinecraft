package net.element.elementsmod.item;

import net.element.elementsmod.ElementsMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static ItemGroup ASPECT_OF_THE_END;

    public static void registerItemGroup() {
        ASPECT_OF_THE_END = FabricItemGroup.builder(new Identifier(ElementsMod.MOD_ID, "aspect_of_the_end"))
                .displayName(Text.literal("Aspect_Of_The_End Item Group"))
                .icon(() -> new ItemStack(ModItems.ASPECT_OF_THE_END)).build();
    }
}
