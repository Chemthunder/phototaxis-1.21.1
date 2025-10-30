package silly.chemthunder.phototaxis.object.entity.moths;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import silly.chemthunder.phototaxis.index.PhototaxisDamageSources;
import silly.chemthunder.phototaxis.index.PhototaxisEntities;
import silly.chemthunder.phototaxis.index.PhototaxisItems;
import silly.chemthunder.phototaxis.util.PhototaxisConfig;

import java.util.List;

public class MothEntity extends TameableEntity implements Flutterer {
    public final AnimationState idleAnimState = new AnimationState();
    public MothEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean isInAir() {
        return true;
    }

    public static DefaultAttributeContainer.Builder createAttribute() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, PhototaxisConfig.mothSpeed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 0)
                .add(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 500)
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
                entity.setAttacker(this.getOwner());
                entity.damage(PhototaxisDamageSources.moth_explode(this), 4f);
            }
        }
        super.onDeath(damageSource);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.25));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 4));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 0.25F, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 0.25f, 10.0F, 2.0F));
        this.targetSelector.add(7, new ActiveTargetGoal<>(this, HuskEntity.class, false));
        super.initGoals();
    }

    @Override
    public boolean tryAttack(Entity target) {
        return super.tryAttack(target);
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world) {
            public boolean isValidPosition(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }
        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.LEAVES);
    }



    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isEmpty()) {
            if (this.getType() == PhototaxisEntities.MOTH) {
                ItemStack mof = new ItemStack(PhototaxisItems.MOTH);
                NbtCompound nbt = new NbtCompound();
                this.saveNbt(nbt);

                if (this.hasCustomName()) {
                    Text custom = this.getCustomName();
                    assert custom != null;
                    mof.set(DataComponentTypes.CUSTOM_NAME, Text.literal(custom.getString()));
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
                    mof.set(DataComponentTypes.CUSTOM_NAME, Text.literal(custom.getString()));
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
                    mof.set(DataComponentTypes.CUSTOM_NAME, Text.literal(custom.getString()));
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
                    mof.set(DataComponentTypes.CUSTOM_NAME, Text.literal(custom.getString()));
                }

                if (player.isSneaking()) {
                    player.giveItemStack(mof);
                    this.discard();
                }
            }
        }

        if (stack.isIn(ItemTags.LEAVES)) {
            this.setTamed(true, false);
            this.setOwner(player);
        }
        return super.interactMob(player, hand);
    }

    @Override
    protected double getGravity() {
        return 0.25;
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
