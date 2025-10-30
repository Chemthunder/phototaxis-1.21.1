package silly.chemthunder.phototaxis.common.index;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.phototaxis.Phototaxis;
import silly.chemthunder.phototaxis.common.entity.PylonEntity;
import silly.chemthunder.phototaxis.common.entity.MothEntity;
import silly.chemthunder.phototaxis.client.entity.MothEntityRenderer;
import silly.chemthunder.phototaxis.client.entity.MothModelEntity;

public interface PhototaxisEntities {
//    EntityType<MissileEntity> MISSILE_ENTITY = create(
//            "magic_missile",
//            EntityType.Builder.create(
//                    MissileEntity::new,
//                    SpawnGroup.MISC
//            ).dimensions(0.2f, 0.2f)
//    );

    EntityType<MothEntity> MOTH = create(
            "moth",
            EntityType.Builder.create(
                    MothEntity::new,
                    SpawnGroup.CREATURE
            ).dimensions(0.6f, 0.3f)
    );

    EntityType<MothEntity> MOTH_SATIN = create(
            "satin_moth",
            EntityType.Builder.create(
                    MothEntity::new,
                    SpawnGroup.CREATURE
            ).dimensions(0.6f, 0.3f)
    );

    EntityType<MothEntity> MOTH_REDHEAD = create(
            "redhead_moth",
            EntityType.Builder.create(
                    MothEntity::new,
                    SpawnGroup.CREATURE
            ).dimensions(0.6f, 0.3f)
    );

    EntityType<MothEntity> DUSTY_MOTH = create(
            "dusty_moth",
            EntityType.Builder.create(
                    MothEntity::new,
                    SpawnGroup.CREATURE
            ).dimensions(0.6f, 0.3f)
    );
    EntityType<PylonEntity> PYLON = create(
            "pylon",
            EntityType.Builder.create(
                    PylonEntity::new,
                    SpawnGroup.MISC
            ).dimensions(0.3f, 0.8f)
    );

    static <T extends Entity> EntityType<T> create(String name, EntityType.Builder<T> builder) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Phototaxis.id(name));
        return Registry.register(Registries.ENTITY_TYPE, key.getValue(), builder.build(String.valueOf(key)));
    }

    static void initialize() {
        // Entities are Registered Statically
        FabricDefaultAttributeRegistry.register(MOTH, MothEntity.createAttribute());
        FabricDefaultAttributeRegistry.register(MOTH_SATIN, MothEntity.createAttribute());
        FabricDefaultAttributeRegistry.register(DUSTY_MOTH, MothEntity.createAttribute());
        FabricDefaultAttributeRegistry.register(MOTH_REDHEAD, MothEntity.createAttribute());
    }

    static void clientInit() {
        EntityModelLayerRegistry.registerModelLayer(MothModelEntity.MOTH_MODEL, MothModelEntity::getTexturedModelData);
        EntityRendererRegistry.register(MOTH, MothEntityRenderer::new);
        EntityRendererRegistry.register(MOTH_SATIN, MothEntityRenderer::new);
        EntityRendererRegistry.register(MOTH_REDHEAD, MothEntityRenderer::new);
        EntityRendererRegistry.register(DUSTY_MOTH, MothEntityRenderer::new);
        EntityRendererRegistry.register(PYLON, EmptyEntityRenderer::new);
    }
}
