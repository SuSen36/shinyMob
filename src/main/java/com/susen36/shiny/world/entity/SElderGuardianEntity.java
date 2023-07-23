package com.susen36.shiny.world.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.level.Level;

public class SElderGuardianEntity extends ElderGuardian {
    public SElderGuardianEntity(EntityType<? extends ElderGuardian> type, Level level) {
        super(type, level);
    }
    public int getAttackDuration() {
        return 80;
    }

    protected SoundEvent getAmbientSound() {
        return  SoundEvents.COPPER_BREAK;
    }

    protected SoundEvent getHurtSound(DamageSource p_32468_) {
        return SoundEvents.COPPER_HIT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COPPER_FALL;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COPPER_FALL;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Guardian.createAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.ARMOR,10.0D);
    }
}
