package net.element.elementsmod;

import net.element.elementsmod.item.ModItems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementsMod implements ModInitializer {
	public static final String MOD_ID = "elementsmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) ->
		{

            /* Manual spectator check is necessary because UseBlockCallbacks
               fire before the spectator check */
			Item item = player.getMainHandStack().getItem();
			String id = Registries.ITEM.getId(item).toString();

			if (!player.isSpectator() && id.equals("aspect_of_the_end"))
			{
				BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
				int x = blockPos.getX();
				int y = blockPos.getY();
				int z = blockPos.getZ();

				player.teleport(x, y, z);
			}
			return ActionResult.PASS;
		});



	}
}
