package silly.chemthunder.phototaxis.common.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import silly.chemthunder.phototaxis.common.entity.PylonEntity;
import silly.chemthunder.phototaxis.common.index.PhototaxisEntities;

public class FoglampItem extends Item {
    public FoglampItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            BlockPos hitPos = world.raycast(new RaycastContext(user.getEyePos(), user.getEyePos().add(user.getRotationVector().multiply(160)), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user)).getBlockPos();
            PylonEntity pylon = new PylonEntity(PhototaxisEntities.PYLON, world);
            pylon.setPos(
                    hitPos.getX() + 0.5f,
                    hitPos.getY() + 1,
                    hitPos.getZ() + 0.5f
            );
            world.spawnEntity(pylon);
        return super.use(world, user, hand);
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}