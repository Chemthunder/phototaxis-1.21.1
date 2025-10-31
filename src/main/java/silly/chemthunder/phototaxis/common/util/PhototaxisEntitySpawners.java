package silly.chemthunder.phototaxis.common.util;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import silly.chemthunder.phototaxis.common.entity.MothEntity;
import silly.chemthunder.phototaxis.common.index.PhototaxisEntities;

public class PhototaxisEntitySpawners {
    public static void initialize() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.TAIGA),
                SpawnGroup.CREATURE, PhototaxisEntities.MOTH, 80, 2, 5);

        SpawnRestriction.register(PhototaxisEntities.MOTH, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.BIRCH_FOREST),
                SpawnGroup.CREATURE, PhototaxisEntities.MOTH_SATIN, 80, 2, 5);

        SpawnRestriction.register(PhototaxisEntities.MOTH_SATIN, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS, BiomeKeys.SAVANNA),
                SpawnGroup.CREATURE, PhototaxisEntities.DUSTY_MOTH, 80, 2, 5);

        SpawnRestriction.register(PhototaxisEntities.DUSTY_MOTH, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SPARSE_JUNGLE, BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE),
                SpawnGroup.CREATURE, PhototaxisEntities.MOTH_REDHEAD, 80, 2, 5);

        SpawnRestriction.register(PhototaxisEntities.MOTH_REDHEAD, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }
}
