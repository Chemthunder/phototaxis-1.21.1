package silly.chemthunder.phototaxis.object.entity.moths;

import net.minecraft.block.AnvilBlock;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import silly.chemthunder.phototaxis.index.PhototaxisItems;

public class MothEntity extends TameableEntity implements Flutterer {
    public final AnimationState idleAnimState = new AnimationState();
    public MothEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }
//    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
//            DataTracker.registerData(MothEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

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
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 5.5)
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
    //
//    @Override
//    protected void initDataTracker(DataTracker.Builder builder) {
//        super.initDataTracker(builder);
//        builder.add(DATA_ID_TYPE_VARIANT, 0);
//    }
//
//    private int getTypeVariant() {
//        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
//    }
//
//    public MothVariant getVariant() {
//        return MothVariant.byId(this.getTypeVariant() & 255);
//    }
//
//    public void setVariant(MothVariant variant) {
//        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
//    }
//
//    @Override
//    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
//        MothVariant variant = Util.getRandom(MothVariant.values(), this.random);
//        setVariant(variant);
//        return super.initialize(world, difficulty, spawnReason, entityData);
//    }
//
//    @Override
//    public void readCustomDataFromNbt(NbtCompound nbt) {
//        super.readCustomDataFromNbt(nbt);
//        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
//    }
//
//    @Override
//    public void writeCustomDataToNbt(NbtCompound nbt) {
//        super.writeCustomDataToNbt(nbt);
//        nbt.putInt("Variant", this.getTypeVariant());
//    }


    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isEmpty()) {
            yesTurnMofIntoItemTypeShi(player);
        }
        return super.interactMob(player, hand);
    }

    public void yesTurnMofIntoItemTypeShi(PlayerEntity player) {
        Hand hand = player.getActiveHand();
        ItemStack playerStack = player.getStackInHand(hand);
        ItemStack mof = new ItemStack(PhototaxisItems.MOTH);
        NbtCompound nbt = new NbtCompound();
        this.saveNbt(nbt);

        if (player.isSneaking()) {
            player.giveItemStack(mof);
        }
        this.discard();
    }
}
