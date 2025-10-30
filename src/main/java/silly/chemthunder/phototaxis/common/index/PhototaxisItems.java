package silly.chemthunder.phototaxis.common.index;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.phototaxis.Phototaxis;
import silly.chemthunder.phototaxis.common.item.FoglampItem;
import silly.chemthunder.phototaxis.common.item.MothItem;
import silly.chemthunder.phototaxis.common.item.MothPeltItem;

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

    Item SATIN_MOTH = create("satin_moth", new MothItem(new Item.Settings()
            .maxCount(1)
            .equipmentSlot((livingEntity, itemStack) -> EquipmentSlot.HEAD)
    ));

    Item DUSTY_MOTH = create("dusty_moth", new MothItem(new Item.Settings()
            .maxCount(1)
            .equipmentSlot((livingEntity, itemStack) -> EquipmentSlot.HEAD)
    ));

    Item MOTH_PELT = create("moth_pelt", new MothPeltItem(new Item.Settings()));

    Item MOTH_SPAWN_EGG = create("moth_spawn_egg", new SpawnEggItem(PhototaxisEntities.MOTH, 0x4a3f20, 0x706034, new Item.Settings()));

    Item SATIN_MOTH_SPAWN_EGG = create("satin_spawn_egg", new SpawnEggItem(PhototaxisEntities.MOTH_SATIN, 0xdccfaf, 0xaca48f, new Item.Settings()));

    Item REDHEAD_MOTH_SPAWN_EGG = create("redhead_spawn_egg", new SpawnEggItem(PhototaxisEntities.MOTH_REDHEAD, 0xa13c34, 0x4c3939, new Item.Settings()));

    Item DUSTY_MOTH_SPAWN_EGG = create("dusty_spawn_egg", new SpawnEggItem(PhototaxisEntities.DUSTY_MOTH, 0xc69c74, 0x90775e, new Item.Settings()));

    static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, Phototaxis.id(name));
        return item;
    }

    static void initialize() {
        ITEMS.forEach((item, id) -> Registry.register(Registries.ITEM, id, item));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(itemGroup -> {
            itemGroup.add(MOTH_SPAWN_EGG);
            itemGroup.add(SATIN_MOTH_SPAWN_EGG);
            itemGroup.add(REDHEAD_MOTH_SPAWN_EGG);
            itemGroup.add(DUSTY_MOTH_SPAWN_EGG);
        });
    }
}
