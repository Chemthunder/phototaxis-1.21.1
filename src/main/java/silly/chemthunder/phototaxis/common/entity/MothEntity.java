package silly.chemthunder.phototaxis.common.entity;

import net.minecraft.client.particle.Particle;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import silly.chemthunder.phototaxis.common.index.PhototaxisDamageSources;
import silly.chemthunder.phototaxis.common.index.PhototaxisEntities;
import silly.chemthunder.phototaxis.common.index.PhototaxisItems;
import silly.chemthunder.phototaxis.common.util.PhototaxisConfig;

import java.util.List;

public class MothEntity extends TameableEntity {
    public final AnimationState idleAnimState = new AnimationState();

    public MothEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 50, false);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder createAttribute() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 3)
                .add(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 500)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 5)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        dropStack(PhototaxisItems.MOTH_PELT.getDefaultStack());
        World world = getWorld();
        if (this.getType() == PhototaxisEntities.DUSTY_MOTH) {
            Box box = new Box(this.getBlockPos()).expand(5, 5, 5);
            List<LivingEntity> entities = getWorld().getEntitiesByClass(
                    LivingEntity.class, box,
                    entity -> true
            );

            for (LivingEntity entity : entities) {
                if (world instanceof ServerWorld serverWorld) {
                entity.setAttacker(this.getOwner());
                entity.damage(PhototaxisDamageSources.moth_explode(this), 4f);

                    serverWorld.spawnParticles(ParticleTypes.FALLING_HONEY, this.getX(), this.getY(), this.getZ(),
                            25,
                            5,
                            5,
                            5,
                            0.05
                    );
                }
            }
        }
        super.onDeath(damageSource);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.25));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 4));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 0.25F, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 0.25f, 10.0F, 2.0F));

        this.targetSelector.add(9, new AttackWithOwnerGoal(this));
        this.targetSelector.add(7, new ActiveTargetGoal<>(this, HuskEntity.class, false));
        super.initGoals();
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.LEAVES);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        LivingEntity owner = this.getOwner();
        // tame
        if (stack.isEmpty()) {
            if (!this.isTamed() || player == owner) {
                if (this.getType() == PhototaxisEntities.MOTH) {
                    ItemStack mof = new ItemStack(PhototaxisItems.MOTH);
                    NbtCompound nbt = new NbtCompound();
                    this.saveNbt(nbt);

                    if (this.hasCustomName()) {
                        Text custom = this.getCustomName();
                        assert custom != null;
                        mof.set(DataComponentTypes.ITEM_NAME, Text.literal(custom.getString()));
                    }

                    if (player.isSneaking()) {
                        player.giveItemStack(mof);
                        this.discard();
                    }
                }
                if (this.getType() == PhototaxisEntities.MOTH_REDHEAD) {
                    ItemStack mof = new ItemStack(PhototaxisItems.REDHEADED_MOTH);
                    NbtCompound nbt = new NbtCompound();
                    this.saveNbt(nbt);

                    if (this.hasCustomName()) {
                        Text custom = this.getCustomName();
                        assert custom != null;
                        mof.set(DataComponentTypes.ITEM_NAME, Text.literal(custom.getString()));
                    }

                    if (player.isSneaking()) {
                        player.giveItemStack(mof);
                        this.discard();
                    }
                }
                if (this.getType() == PhototaxisEntities.MOTH_SATIN) {
                    ItemStack mof = new ItemStack(PhototaxisItems.SATIN_MOTH);
                    NbtCompound nbt = new NbtCompound();
                    this.saveNbt(nbt);

                    if (this.hasCustomName()) {
                        Text custom = this.getCustomName();
                        assert custom != null;
                        mof.set(DataComponentTypes.ITEM_NAME, Text.literal(custom.getString()));
                    }

                    if (player.isSneaking()) {
                        player.giveItemStack(mof);
                        this.discard();
                    }
                }
                if (this.getType() == PhototaxisEntities.DUSTY_MOTH) {
                    ItemStack mof = new ItemStack(PhototaxisItems.DUSTY_MOTH);
                    NbtCompound nbt = new NbtCompound();
                    this.saveNbt(nbt);

                    if (this.hasCustomName()) {
                        Text custom = this.getCustomName();
                        assert custom != null;
                        mof.set(DataComponentTypes.ITEM_NAME, Text.literal(custom.getString()));
                    }

                    if (player.isSneaking()) {
                        player.giveItemStack(mof);
                        this.discard();
                    }
                }
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    public boolean shouldRenderName() {
        return PhototaxisConfig.showMothNames;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_AZALEA_LEAVES_BREAK;
    }
}
