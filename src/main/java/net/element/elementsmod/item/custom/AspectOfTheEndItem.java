package net.element.elementsmod.item.custom;


import net.element.elementsmod.ElementsMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;



public class AspectOfTheEndItem extends SwordItem {
    public AspectOfTheEndItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getPlayer().isSpectator() || context.getWorld().isClient()) return ActionResult.PASS;
        return ActionResult.SUCCESS;
    }

    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.COBWEB)) {
            return 15.0f;
        }
        Material material = state.getMaterial();
        if (material == Material.PLANT || material == Material.REPLACEABLE_PLANT || state.isIn(BlockTags.LEAVES) || material == Material.GOURD) {
            return 1.5f;
        }
        return 1.0f;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        Vec3d startPos = player.getEyePos();
        Vec3d endPos = startPos.add(player.getRotationVector().multiply(7));
        BlockHitResult hitResult = world.raycast(new RaycastContext(startPos, endPos, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
        ElementsMod.LOGGER.info(hitResult.getType().toString());

        player.getItemCooldownManager().set(this, 90);

        //check if the block above the one that is targeted and the block that is 2 blocks above the targeted block are air blocks. 
        //If yes, teleport the player to the block above the targeted block.
        if(world.getBlockState(hitResult.getBlockPos().up(1)).isAir() &&
           world.getBlockState(hitResult.getBlockPos().up(2)).isAir()) {

            player.teleport(hitResult.getBlockPos().getX()+0.5, hitResult.getBlockPos().getY() + 1, hitResult.getBlockPos().getZ()+0.5);
            return TypedActionResult.success(player.getStackInHand(hand));

        }
        else return TypedActionResult.pass(player.getStackInHand(hand));


        
    }
}
