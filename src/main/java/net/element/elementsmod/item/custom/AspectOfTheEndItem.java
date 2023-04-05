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
import net.minecraft.util.hit.HitResult.Type;
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

        BlockPos blockPos = context.getBlockPos();
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();

        //teleporting the player one block above the block's location as otherwise the player ends up inside the block
        context.getPlayer().teleport(x, y+1 ,z);

        context.getPlayer().getItemCooldownManager().set(this, 120);

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
        Vec3d endPos = startPos.add(player.getRotationVector().multiply(5));
        BlockHitResult hitResult = world.raycast(new RaycastContext(startPos, endPos, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
        ElementsMod.LOGGER.info(hitResult.getType().toString());

        player.getItemCooldownManager().set(this, 120);

        //check if the block that is targeted and the block above it is an air block, if yes, teleport the player to it
        if(hitResult.getType() == Type.MISS && world.getBlockState(hitResult.getBlockPos().up(1)).isAir()) {

            player.teleport(hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ());
            return TypedActionResult.success(player.getStackInHand(hand));

        }
        else return TypedActionResult.pass(player.getStackInHand(hand));


        
    }
}
