package com.susen36.shiny.world.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class SPhantomEntity extends Phantom {
    public SPhantomEntity(EntityType<? extends Phantom> type, Level level) {
        super(type, level);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Monster.class, 32, true, false, (entity) -> {
            return entity instanceof Enemy && entity.getMobType()== MobType.UNDEAD;
        }));
       }
    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);
        if (flag  &&entity instanceof Zombie) {
            ((Zombie) entity).addEffect(new MobEffectInstance(MobEffects.HEAL));
        }
        return flag;
    }

    @Override
    protected void tickDeath() {
        if (!this.level().isClientSide()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.0F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 3);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float) areaeffectcloud.getDuration());
            areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.HUNGER,300));
            this.level().addFreshEntity(areaeffectcloud);
        }
        super.tickDeath();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.FLYING_SPEED, 0.235F).add(Attributes.FOLLOW_RANGE, 32D);
    }
}
