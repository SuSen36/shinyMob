package com.susen36.shiny.world.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;

public class SSnowGolemEntity extends SnowGolem {
    public SSnowGolemEntity(EntityType<? extends SnowGolem> p_29902_, Level p_29903_) {
        super(p_29902_, p_29903_);
        setPumpkin(false);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (entity) -> {
            return entity instanceof Enemy && !(entity instanceof Creeper);
        }));
    }
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {

            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this)) {
                return;
            }
            FrostWalkerEnchantment.onEntityMoved(this, this.level(), this.blockPosition(), (int) Math.max(3 * this.getScale(), 1));

        }
    }
    public boolean doHurtTarget(Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        } else {
            if (entity instanceof LivingEntity) {
                int i = entity.getTicksFrozen();
                entity.setTicksFrozen(Math.min(this.getTicksRequiredToFreeze(), i + 1));
            }
            return true;
        }
    }
    public boolean isSensitiveToWater() {
        return false;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 18.0D).add(Attributes.MOVEMENT_SPEED, (double)0.25F).add(Attributes.ATTACK_DAMAGE,6);
    }

}
