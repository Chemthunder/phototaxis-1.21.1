package silly.chemthunder.phototaxis.index;

import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.phototaxis.Phototaxis;
import silly.chemthunder.phototaxis.object.entity.moths.MothEntity;
import silly.chemthunder.phototaxis.object.item.*;

import java.util.LinkedHashMap;
import java.util.Map;

public interface PhototaxisItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();


    // Item DAMNED_BOOK = create("damned_book", new DamnedBookItem(new Item.Settings()
    //            .maxCount(1)
    //            .fireproof()
    //            .rarity(Rarity.UNCOMMON)
    //    ));

    Item FOGLAMP = create("foglamp", new FoglampItem(new Item.Settings()
            .maxCount(1)
    ));

    Item MOTH = create("moth", new MothItem(new Item.Settings()
            .maxCount(1)
            .equipmentSlot((livingEntity, itemStack) -> EquipmentSlot.HEAD)
    ));

    Item REDHEADED_MOTH = create("redheaded_moth", new MothItem(new Item.Settings()
            .maxCount(1)
            .equipmentSlot((livingEntity, itemStack) -> EquipmentSlot.HEAD)
    ));

    Item MOTH_PELT = create("moth_pelt", new MothPeltItem(new Item.Settings()));

    Item MOTH_SPAWN_EGG = create("moth_spawn_egg", new SpawnEggItem(PhototaxisEntities.MOTH, 0x4a3f20, 0x706034, new Item.Settings()));

    Item SATIN_MOTH_SPAWN_EGG = create("satin_spawn_egg", new SpawnEggItem(PhototaxisEntities.MOTH_SATIN, 0x4a3f20, 0x706034, new Item.Settings()));

    Item REDHEAD_MOTH_SPAWN_EGG = create("redhead_spawn_egg", new SpawnEggItem(PhototaxisEntities.MOTH_REDHEAD, 0x4a3f20, 0x706034, new Item.Settings()));

    Item DUSTY_MOTH_SPAWN_EGG = create("dusty_spawn_egg", new SpawnEggItem(PhototaxisEntities.DUSTY_MOTH, 0x4a3f20, 0x706034, new Item.Settings()));

    static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, Phototaxis.id(name));
        return item;
    }

    static void initialize() {
        ITEMS.forEach((item, id) -> Registry.register(Registries.ITEM, id, item));

        Phototaxis.LOGGER.info("items have been initialized");
    }
}
