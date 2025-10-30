package silly.chemthunder.phototaxis.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import silly.chemthunder.phototaxis.Phototaxis;

public interface PhototaxisItemGroups {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Phototaxis.id("phototaxis"));
    ItemGroup A_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(PhototaxisItems.MOTH))
            .displayName(Text.translatable("itemgroup.phototaxis"))
            .build();

    static void initialize() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, A_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(PhototaxisItemGroups::addEntries);

    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(PhototaxisItems.FOGLAMP);
        itemGroup.add(PhototaxisItems.MOTH);
        itemGroup.add(PhototaxisItems.REDHEADED_MOTH);
        itemGroup.add(PhototaxisItems.SATIN_MOTH);
        itemGroup.add(PhototaxisItems.DUSTY_MOTH);
        itemGroup.add(PhototaxisItems.MOTH_PELT);
    }
}
