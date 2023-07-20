package com.susen36.shiny.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SBlazeEntity extends Blaze {
    public SBlazeEntity(EntityType<? extends Blaze> blaze, Level level) {
        super(blaze, level);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.6D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Pillager.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
    public void aiStep() {
        if (this.level().isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level().addParticle(ParticleTypes.SOUL, this.getRandomX(0.8D), this.getRandomY(), this.getRandomZ(0.8D), 0.0D, 0.0D, 0.0D);
            }
        }
        super.aiStep();
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;

        if (this.deathTime == 50) {
            if (!this.level().isClientSide()) {
               // boolean mobGriefing = ForgeEventFactory.getMobGriefingEvent(this.level(), this);
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3, Level.ExplosionInteraction.NONE);
            }
            this.deathTime = 19;
            super.tickDeath();
            this.deathTime = 50;
        }
    }
    public boolean doHurtTarget(Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        } else {
            if (entity instanceof LivingEntity &&!entity.fireImmune()) {
                entity.setSecondsOnFire((int) (this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()/2));
                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200), this);
            }
            return true;
        }
    }
    protected void checkFallDamage(double p_19911_, boolean p_19912_, BlockState p_19913_, BlockPos p_19914_) {return;}
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 8.0D).add(Attributes.MOVEMENT_SPEED, (double)0.27F).add(Attributes.FOLLOW_RANGE, 48.0D);
    }
}
