package silly.chemthunder.phototaxis.index;

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
import silly.chemthunder.phototaxis.object.entity.PylonEntity;
import silly.chemthunder.phototaxis.object.entity.moths.MothEntity;
import silly.chemthunder.phototaxis.object.entity.moths.client.MothEntityRenderer;
import silly.chemthunder.phototaxis.object.entity.moths.client.MothModelEntity;

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
    }

    static void clientInit() {
        EntityModelLayerRegistry.registerModelLayer(MothModelEntity.MOTH_MODEL, MothModelEntity::getTexturedModelData);
        EntityRendererRegistry.register(MOTH, MothEntityRenderer::new);
        EntityRendererRegistry.register(PYLON, EmptyEntityRenderer::new);
    }
}
