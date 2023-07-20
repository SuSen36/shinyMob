package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SPufferFishEntity extends Animal {
    private static final EntityDataAccessor<Boolean> PUFF_STATE = SynchedEntityData.defineId(SPufferFishEntity.class, EntityDataSerializers.BOOLEAN);

    public SPufferFishEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.refreshDimensions();
        setNoGravity(this.getPuffState());
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.COD), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob p_146744_) {
        return EntityInit.SHINY_PUFFERFISH.get().create(level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PUFF_STATE, false);
    }

    public Boolean getPuffState() {
        return this.entityData.get(PUFF_STATE);
    }

    public void setPuffState(boolean p_29619_) {
        this.entityData.set(PUFF_STATE, p_29619_);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_29615_) {
        if (PUFF_STATE.equals(p_29615_)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(p_29615_);
    }

    public void addAdditionalSaveData(CompoundTag p_29624_) {
        super.addAdditionalSaveData(p_29624_);
        p_29624_.putBoolean("PuffState", this.getPuffState());
    }

    public void readAdditionalSaveData(CompoundTag p_29613_) {
        super.readAdditionalSaveData(p_29613_);
        this.setPuffState(p_29613_.getBoolean("PuffState"));
    }
    public EntityDimensions getDimensions(Pose p_29608_) {
        return super.getDimensions(p_29608_).scale(this.getScale());
    }
    @Override
    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && this.isEffectiveAi()) {
            if(this.hasEffect(MobEffects.LEVITATION) && !this.isBaby()){
                this.setPuffState(true);
            }else {
                this.setPuffState(false);
            }
        }
        super.tick();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        super.hurt(source,amount);
        if (source.getDirectEntity() instanceof AbstractArrow) {
            super.hurt(source, amount*2);
        } else if(!this.isBaby()){
            this.addEffect(new MobEffectInstance(MobEffects.LEVITATION,200));
        }
        return super.hurt(source, amount*2);
    }

    public float getScale() {
        return this.isBaby() ? 0.4F : this.getPuffState() ? 1.0F : 0.7F;
    }
    public void playerTouch(Player player) {
        Boolean i = this.getPuffState();
        if (player instanceof ServerPlayer && i && player.hurt(this.damageSources().mobAttack(this), (float)(1))) {
            if (!this.isSilent()) {
                ((ServerPlayer) player).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
            }
            this.doHurtTarget(player);
        }
    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.PUFFER_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PUFFER_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_29628_) {
        return SoundEvents.PUFFER_FISH_HURT;
    }

    protected void checkFallDamage(double p_19911_, boolean p_19912_, BlockState p_19913_, BlockPos p_19914_) {return;}

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.ATTACK_DAMAGE,1).add(Attributes.MOVEMENT_SPEED,0.35f);
    }

}
