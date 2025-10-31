package silly.chemthunder.phototaxis.common.index;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import silly.chemthunder.phototaxis.Phototaxis;

public interface PhototaxisTags {
    TagKey<Item> MOTHS = createItemTag("moths");

    private static TagKey<Item> createItemTag(String id) {
        return TagKey.of(RegistryKeys.ITEM, Phototaxis.id(id));
    }
}