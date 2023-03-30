package net.element.elementsmod.item;

import net.element.elementsmod.ElementsMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroup {

    private static final ItemGroup ELEMENTSMODITEMGROUP = FabricItemGroup.builder(new Identifier(ElementsMod.MOD_ID, "Elements Mod Group"))
            .icon(() -> new ItemStack(ModItems.ASPECT_OF_THE_END))
            .entries((context, entries) -> {
                entries.add(ModItems.ASPECT_OF_THE_END);
            })
            .build();

}
