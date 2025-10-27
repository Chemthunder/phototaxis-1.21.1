package silly.chemthunder.phototaxis.object.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import silly.chemthunder.phototaxis.util.PhototaxisConfig;

import java.util.List;

public class PylonEntity extends Entity {
    public PylonEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public void tick() {
        if (getWorld() != null) {
            if (PhototaxisConfig.applyParticles) {
                MinecraftClient.getInstance().particleManager.addParticle(ParticleTypes.END_ROD,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        0,
                        0.5,
                        0
                );
            } else {
                MinecraftClient.getInstance().particleManager.addParticle(ParticleTypes.END_ROD,
                        this.getX(),
                        this.getY() + 1,
                        this.getZ(),
                        0,
                        0,
                        0
                );
            }
        }
        Box box = new Box(this.getBlockPos()).expand(5, 15, 5);
        List<LivingEntity> entities = getWorld().getEntitiesByClass(
                LivingEntity.class, box,
                entity -> true
        );

        for (LivingEntity entity : entities) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100));
        }
        super.tick();
    }
}
