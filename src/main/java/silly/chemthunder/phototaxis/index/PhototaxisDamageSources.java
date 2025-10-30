package silly.chemthunder.phototaxis.index;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.phototaxis.Phototaxis;

public interface PhototaxisDamageSources {
    RegistryKey<DamageType> MOTH_EXPLODE = of("moth_explode");

    static DamageSource moth_explode(LivingEntity entity) {
        return entity.getDamageSources().create(MOTH_EXPLODE); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Phototaxis.id(name));
    }
}
