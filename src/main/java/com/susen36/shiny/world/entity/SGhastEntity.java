package com.susen36.shiny.world.entity;

import com.susen36.shiny.world.entity.ai.FlyingAttackGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SGhastEntity extends Ghast {
    public SGhastEntity(EntityType<? extends Ghast> ghast, Level level) {
        super(ghast, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.xpReward=1;
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new FlyingAttackGoal(this));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (entity) -> {
            return this.getMaxHealth()*1.5f>=entity.getHealth();
        }));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Villager.class, 10, true, false, (entity) -> {
            return this.getMaxHealth()*1.5f>=entity.getHealth();
        }));
    }
    public void tick() {
        super.tick();
        if (!this.level().isClientSide&&this.getTarget()!=null &&this.distanceToSqr(this.getTarget())<=4) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1, Level.ExplosionInteraction.NONE);
            this.remove(RemovalReason.KILLED);
        }
    }
    public boolean hurt(DamageSource source, float p_32731_) {
        if(!this.isInvulnerableTo(source)) {
            this.remove(RemovalReason.KILLED);
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1, Level.ExplosionInteraction.NONE);
            return true;
            }
        return false;
    }

   protected float getSoundVolume() {
       return 1.5F;
   }
   public int getMaxSpawnClusterSize() {
       return 8;
   }
   protected float getStandingEyeHeight(Pose p_32741_, EntityDimensions p_32742_) {
       return 0.37F;
   }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FOLLOW_RANGE, 100.0D).add(Attributes.FLYING_SPEED, 0.47f).add(Attributes.KNOCKBACK_RESISTANCE,10);
    }
}
