package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.IForgeShearable;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SMushroomCowEntity extends Cow implements Shearable, IForgeShearable {
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(SMushroomCowEntity.class, EntityDataSerializers.STRING);
    @Nullable
    private MobEffect effect;
    private int effectDuration;
    @Nullable
    private UUID lastLightningBoltUUID;

    public SMushroomCowEntity(EntityType<? extends Cow> p_28914_, Level p_28915_) {
        super(p_28914_, p_28915_);
    }

    public float getWalkTargetValue(BlockPos p_28933_, LevelReader p_28934_) {
        return p_28934_.getBlockState(p_28933_.below()).is(Blocks.MYCELIUM) ? 10.0F : p_28934_.getPathfindingCostFromLightLevels(p_28933_);
    }

    public static boolean checkMushroomSpawnRules(EntityType<SMushroomCowEntity> cow, LevelAccessor p_218202_, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return p_218202_.getBlockState(pos.below()).is(BlockTags.MOOSHROOMS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_218202_, pos);
    }

    public void thunderHit(ServerLevel p_28921_, LightningBolt p_28922_) {
        UUID uuid = p_28922_.getUUID();
        if (!uuid.equals(this.lastLightningBoltUUID)) {
            this.setVariant(this.getVariant() == FungusType.WART ? random.nextInt(2) == 0 ? FungusType.CRIMSON : FungusType.WARPED : FungusType.WART);
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEvents.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, FungusType.CRIMSON.type);
    }

    public InteractionResult mobInteract(Player p_28941_, InteractionHand p_28942_) {
        ItemStack itemstack = p_28941_.getItemInHand(p_28942_);
        if (itemstack.is(Items.BOWL) && !this.isBaby()) {
            boolean flag = false;
            ItemStack itemstack1;
            if (this.effect != null) {
                flag = true;
                itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
                SuspiciousStewItem.saveMobEffect(itemstack1, this.effect, this.effectDuration);
                this.effect = null;
                this.effectDuration = 0;
            } else {
                itemstack1 = new ItemStack(Items.MUSHROOM_STEW);
            }

            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_28941_, itemstack1, false);
            p_28941_.setItemInHand(p_28942_, itemstack2);
            SoundEvent soundevent;
            if (flag) {
                soundevent = SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY;
            } else {
                soundevent = SoundEvents.MOOSHROOM_MILK;
            }

            this.playSound(soundevent, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (false && itemstack.getItem() == Items.SHEARS && this.readyForShearing()) { //Forge: Moved to onSheared
            this.shear(SoundSource.PLAYERS);
            this.gameEvent(GameEvent.SHEAR, p_28941_);
            if (!this.level().isClientSide) {
                itemstack.hurtAndBreak(1, p_28941_, (p_28927_) -> {
                    p_28927_.broadcastBreakEvent(p_28942_);
                });
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (this.getVariant() == FungusType.WARPED && itemstack.is(ItemTags.SMALL_FLOWERS)) {
            if (this.effect != null) {
                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.SMOKE, this.getX() + this.random.nextDouble() / 2.0D, this.getY(0.5D), this.getZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
                }
            } else {
                Optional<Pair<MobEffect, Integer>> optional = this.getEffectFromItemStack(itemstack);
                if (!optional.isPresent()) {
                    return InteractionResult.PASS;
                }

                Pair<MobEffect, Integer> pair = optional.get();
                if (!p_28941_.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                for(int j = 0; j < 4; ++j) {
                    this.level().addParticle(ParticleTypes.EFFECT, this.getX() + this.random.nextDouble() / 2.0D, this.getY(0.5D), this.getZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
                }

                this.effect = pair.getLeft();
                this.effectDuration = pair.getRight();
                this.playSound(SoundEvents.MOOSHROOM_EAT, 2.0F, 1.0F);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(p_28941_, p_28942_);
        }
    }

    @Override
    public java.util.List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
        this.gameEvent(GameEvent.SHEAR, player);
        return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
    }

    public void shear(SoundSource p_28924_) {
        shearInternal(p_28924_).forEach(s -> this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(1.0D), this.getZ(), s)));
    }

    private java.util.List<ItemStack> shearInternal(SoundSource p_28924_) {
        this.level().playSound((Player)null, this, SoundEvents.MOOSHROOM_SHEAR, p_28924_, 1.0F, 1.0F);
        if (!this.level().isClientSide()) {
            Cow cow = EntityType.COW.create(this.level());
            if (cow != null) {
                ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                this.discard();
                cow.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                cow.setHealth(this.getHealth());
                cow.yBodyRot = this.yBodyRot;
                if (this.hasCustomName()) {
                    cow.setCustomName(this.getCustomName());
                    cow.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistenceRequired()) {
                    cow.setPersistenceRequired();
                }

                cow.setInvulnerable(this.isInvulnerable());
                this.level().addFreshEntity(cow);

                java.util.List<ItemStack> items = new java.util.ArrayList<>();
                for(int i = 0; i < 5; ++i) {
                    items.add(new ItemStack(this.getVariant().blockState.getBlock()));
                }
                return items;
            }
        }
        return java.util.Collections.emptyList();

    }

    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby();
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Type", this.getVariant().getSerializedName());
        if (this.effect != null) {
            tag.putInt("EffectId", MobEffect.getId(this.effect));
            net.minecraftforge.common.ForgeHooks.saveMobEffect(tag, "forge:effect_id", this.effect);
            tag.putInt("EffectDuration", this.effectDuration);
        }

    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(FungusType.byType(tag.getString("Type")));
        if (tag.contains("EffectId", 99)) {
            this.effect = MobEffect.byId(tag.getInt("EffectId"));
            this.effect = net.minecraftforge.common.ForgeHooks.loadMobEffect(tag, "forge:effect_id", this.effect);
        }

        if (tag.contains("EffectDuration", 99)) {
            this.effectDuration = tag.getInt("EffectDuration");
        }

    }

    private Optional<Pair<MobEffect, Integer>> getEffectFromItemStack(ItemStack p_28957_) {
        SuspiciousEffectHolder suspiciouseffectholder = SuspiciousEffectHolder.tryGet(p_28957_.getItem());
        return suspiciouseffectholder != null ? Optional.of(Pair.of(suspiciouseffectholder.getSuspiciousEffect(), suspiciouseffectholder.getEffectDuration())) : Optional.empty();
    }

    public void setVariant(FungusType p_28929_) {
        this.entityData.set(DATA_TYPE, p_28929_.type);
    }

    public FungusType getVariant() {
        return FungusType.byType(this.entityData.get(DATA_TYPE));
    }

    @Nullable
    public SMushroomCowEntity getBreedOffspring(ServerLevel level, AgeableMob p_148943_) {
        SMushroomCowEntity mushroomcow = EntityInit.SHINY_MOOSHROOM.get().create(level);
        if (mushroomcow != null) {
            mushroomcow.setVariant(this.getOffspringType((SMushroomCowEntity)p_148943_));
        }

        return mushroomcow;
    }

    private FungusType getOffspringType(SMushroomCowEntity cow) {
        FungusType mushroomtype = this.getVariant();
        FungusType mushroomtype1 = cow.getVariant();
        FungusType mushroomtype2;

        if (mushroomtype == mushroomtype1 && this.random.nextInt(16) == 0) {
            mushroomtype2 = mushroomtype == FungusType.CRIMSON ? FungusType.WARPED : FungusType.CRIMSON;
        } else {
            mushroomtype2 = this.random.nextBoolean() ? mushroomtype : mushroomtype1;
        }

        return mushroomtype2;
    }

    @Override
    public boolean isShearable(@org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos) {
        return readyForShearing();
    }

    public static enum FungusType implements StringRepresentable {
        WART("wart", Blocks.NETHER_WART.defaultBlockState()),
        CRIMSON("crimson", Blocks.CRIMSON_FUNGUS.defaultBlockState()),
        WARPED("warped", Blocks.WARPED_FUNGUS.defaultBlockState());

        public static final StringRepresentable.EnumCodec<FungusType> CODEC = StringRepresentable.fromEnum(FungusType::values);
        final String type;
        final BlockState blockState;

        private FungusType(String p_28967_, BlockState p_28968_) {
            this.type = p_28967_;
            this.blockState = p_28968_;
        }

        public BlockState getBlockState() {
            return this.blockState;
        }

        public String getSerializedName() {
            return this.type;
        }

        static FungusType byType(String p_28977_) {
            return CODEC.byName(p_28977_, WART);
        }
    }
}
