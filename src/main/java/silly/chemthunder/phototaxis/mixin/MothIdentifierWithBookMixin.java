package silly.chemthunder.phototaxis.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import silly.chemthunder.phototaxis.object.entity.moths.MothEntity;

@Mixin(BookItem.class)
public abstract class MothIdentifierWithBookMixin extends Item {
    public MothIdentifierWithBookMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof MothEntity) {
            user.sendMessage(Text.translatable(entity.getType().getTranslationKey().formatted(Formatting.GRAY).formatted(Formatting.ITALIC)), true);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
