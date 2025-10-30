package silly.chemthunder.phototaxis.common.item;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.data.AreaLightData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import silly.chemthunder.phototaxis.common.index.PhototaxisEnchantments;
import silly.chemthunder.phototaxis.common.index.PhototaxisEntities;
import silly.chemthunder.phototaxis.common.entity.PylonEntity;
import silly.chemthunder.phototaxis.common.util.PhototaxisConfig;

public class FoglampItem extends Item {
    public FoglampItem(Settings settings) {
        super(settings);
    }



    public static final AreaLightData light = new AreaLightData();

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (EnchantmentHelper.hasAnyEnchantmentsWith(user.getStackInHand(user.getActiveHand()), PhototaxisEnchantments.CARYATID)) {
            BlockPos hitPos = world.raycast(new RaycastContext(user.getEyePos(), user.getEyePos().add(user.getRotationVector().multiply(160)), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user)).getBlockPos();
            PylonEntity pylon = new PylonEntity(PhototaxisEntities.PYLON, world);
            pylon.setPos(
                    hitPos.getX(),
                    hitPos.getY() + 1,
                    hitPos.getZ()
            );
            world.spawnEntity(pylon);
        } else {
            user.sendMessage(Text.translatable("text.foglamp.debug"), true);
            if (PhototaxisConfig.foglampVeilLight) {
                VeilRenderSystem.renderer().getLightRenderer().addLight(light);
                light.setAngle((float) Math.toRadians(35));
                light.setBrightness(0.7f);
                light.setDistance(30);
                light.setBrightness(2);
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            if (PhototaxisConfig.foglampVeilLight) {
                ItemStack main = player.getMainHandStack();
                float partialTicks = MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false);
                float yawRad = (float) Math.toRadians(player.getYaw());
                float pitchRad = (float) Math.toRadians(player.getPitch());
                Vec3d cameraPosVec = player.getCameraPosVec(partialTicks).subtract(player.getRotationVec(partialTicks).multiply(0.3F));

                Quaternionf quat = new Quaternionf()
                        .rotateY(yawRad)
                        .rotateX(pitchRad);

                if (main.isOf(this)) {
                    light.getOrientation().identity().rotateXYZ((float) (-Math.toRadians(player.getPitch(partialTicks))), (float) (Math.toRadians(player.getYaw(partialTicks))), 0.0F);
                    light.getPosition().set(cameraPosVec.getX(), cameraPosVec.getY(), cameraPosVec.getZ());

                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}