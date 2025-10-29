package silly.chemthunder.phototaxis.object.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import silly.chemthunder.phototaxis.index.PhototaxisEntities;
import silly.chemthunder.phototaxis.index.PhototaxisItems;
import silly.chemthunder.phototaxis.object.entity.moths.MothEntity;

import java.util.List;

public class MothItem extends Item {
    public MothItem(Settings settings) {
        super(settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(this.getDescription().formatted(Formatting.GRAY));
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            if (player.getOffHandStack().isEmpty()) {
                Hand hand = player.getActiveHand();
                ItemStack stack = player.getStackInHand(hand);
                World world = context.getWorld();

                Vec3d spawnPos = context.getHitPos();


                //   world.spawnEntity(this.getSend(world, player.getStackInHand(hand), context.getHitPos(), player));

                if (stack.isOf(PhototaxisItems.MOTH)) {
                    MothEntity moth = new MothEntity(PhototaxisEntities.MOTH, world);

                    moth.updatePosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                    moth.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                    moth.setSitting(false);
                    moth.setOwner(player);
                }
                player.setStackInHand(hand, ItemStack.EMPTY);
            }
        }
        return super.useOnBlock(context);
    }
//
//    public MothEntity getSend(World world, ItemStack mothStack, Vec3d spawnPos, PlayerEntity player) {
//        if (mothStack.isOf(PhototaxisItems.MOTH)) {
//            MothEntity moth = PhototaxisEntities.MOTH.create(world);
//            assert moth != null;
//            moth.updatePosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
//            moth.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
//            moth.setSitting(false);
//            moth.setOwner(player);
//        }
//        if (mothStack.isOf(PhototaxisItems.REDHEADED_MOTH)) {
//            MothEntity moth = PhototaxisEntities.MOTH_REDHEAD.create(world);
//            assert moth != null;
//            moth.updatePosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
//            moth.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
//            moth.setSitting(false);
//            moth.setOwner(player);
//        }
//
//        return ActionResult.;
//    }
}
