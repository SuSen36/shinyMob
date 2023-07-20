package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.ItemsInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SCodEntity extends Cod  {
    public SCodEntity(EntityType<? extends Cod> cod, Level level) {
        super(cod, level);
    }

    public ItemStack getBucketItemStack() {return new ItemStack(ItemsInit.SHINY_COD_BUCKET.get());}

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_28281_) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }
}
