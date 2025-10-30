package silly.chemthunder.phototaxis.object.entity.moths;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
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
//    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
//            DataTracker.registerData(MothEntity.class, TrackedDataHandlerRegistry.INTEGER);



    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean isInAir() {
        return false;
    }

    public static DefaultAttributeContainer.Builder createAttribute() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, PhototaxisConfig.mothSpeed)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 0)
                .add(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 500)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        dropStack(PhototaxisItems.MOTH_PELT.getDefaultStack());
        super.onDeath(damageSource);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.25));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 4));
        this.goalSelector.add(6, new LookAroundGoal(this));
        super.initGoals();
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
        return false;
    }

    private String newItemName;

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

    @Override
    public void onRemoved() {
        if (this.getType() == PhototaxisEntities.DUSTY_MOTH) {
            Box box = new Box(this.getBlockPos()).expand(5, 5, 5);
            List<LivingEntity> entities = getWorld().getEntitiesByClass(
                    LivingEntity.class, box,
                    entity -> true
            );

            for (LivingEntity entity : entities) {
                if (!getWorld().isClient) {
                    if (!(entity instanceof MothEntity) || entity != this.getOwner())
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100));
                }
            }
        }
        super.onRemoved();
    }
}
