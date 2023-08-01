package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SFoxEntity extends Fox {
    public SFoxEntity(EntityType<? extends Fox> type, Level level) {
        super(type, level);
    }
    public void aiStep() {
        super.aiStep();

        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D && !this.hasEffect(MobEffects.SLOW_FALLING)) {
                this.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,60));
        }
    }
    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);
        if (flag && this.getMainHandItem().isEmpty() && entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.LEVITATION,200));
        }
        return flag;
    }
    @Nullable
    public Fox getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
        return EntityInit.SHINY_FOX.get().create(p_149001_);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.FOLLOW_RANGE, 32.0D).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
}
