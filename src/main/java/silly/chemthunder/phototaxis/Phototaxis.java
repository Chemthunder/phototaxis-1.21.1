package silly.chemthunder.phototaxis;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silly.chemthunder.phototaxis.common.index.PhototaxisEntities;
import silly.chemthunder.phototaxis.common.index.PhototaxisItemGroups;
import silly.chemthunder.phototaxis.common.index.PhototaxisItems;
import silly.chemthunder.phototaxis.common.util.PhototaxisConfig;
import silly.chemthunder.phototaxis.common.util.PhototaxisEntitySpawners;

public class Phototaxis implements ModInitializer {
	public static final String MOD_ID = "phototaxis";

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path); }
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        PhototaxisItems.initialize();
        PhototaxisEntities.initialize();
        PhototaxisItemGroups.initialize();
        PhototaxisEntitySpawners.initialize();

        // modmenu
        MidnightConfig.init(MOD_ID, PhototaxisConfig.class);
        ALib.registerModMenu(MOD_ID, 0x274f2b);

		LOGGER.debug("Phototaxis implementation debug environment begun");
	}
}