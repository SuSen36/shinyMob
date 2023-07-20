package com.susen36.shiny.world.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.level.Level;

public class SMagaCubeEntity extends MagmaCube {
    private static final EntityDataAccessor<Boolean> SOLIDIFY = SynchedEntityData.defineId(SMagaCubeEntity.class, EntityDataSerializers.BOOLEAN);

    public SMagaCubeEntity(EntityType<? extends MagmaCube> type, Level level) {
        super(type, level);
    }
    public void tick(){
        super.tick();
        if(!this.level().isClientSide){
        if (this.isSensitiveToWater() && this.isInWaterRainOrBubble()&&!this.getSolidState()) {
            setSolidState(true);
        }
        if(this.getSolidState()){
            this.getAttribute(Attributes.ARMOR).setBaseValue((double)(6+this.getAttribute(Attributes.ARMOR).getBaseValue()*2));
        }
       }else if (this.level().isClientSide&&this.getLastDamageSource()==this.damageSources().drown()){
            for(int i = 0; i < 3; ++i) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(0.8D), this.getRandomY(), this.getRandomZ(0.8D), 0.0D, 0.0D, 0.0D);
            }
        }
    }
    @Override
    public void remove(Entity.RemovalReason removalReason) {
        this.setRemoved(removalReason);
        this.invalidateCaps();
        this.brain.clearMemories();
    }
    public boolean isSensitiveToWater() {
        return !getSolidState();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SOLIDIFY, false);
    }

    public Boolean getSolidState() {
        return this.entityData.get(SOLIDIFY);
    }

    public void setSolidState(boolean p_29619_) {
        this.entityData.set(SOLIDIFY, p_29619_);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_29615_) {
        if (SOLIDIFY.equals(p_29615_)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(p_29615_);
    }

    public void addAdditionalSaveData(CompoundTag p_29624_) {
        super.addAdditionalSaveData(p_29624_);
        p_29624_.putBoolean("Solidify", this.getSolidState());
    }

    public void readAdditionalSaveData(CompoundTag p_29613_) {
        super.readAdditionalSaveData(p_29613_);
        this.setSolidState(p_29613_.getBoolean("Solidify"));
    }
}
