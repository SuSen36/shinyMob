package com.susen36.shiny.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SMagmaCubeEntity extends MagmaCube {
    private static final EntityDataAccessor<Boolean> SOLIDIFY = SynchedEntityData.defineId(SMagmaCubeEntity.class, EntityDataSerializers.BOOLEAN);

    public SMagmaCubeEntity(EntityType<? extends MagmaCube> type, Level level) {
        super(type, level);
    }
    public void tick(){
        super.tick();
        if(!this.level().isClientSide){
        if (this.isSensitiveToWater() && this.isInWaterRainOrBubble()&&!this.getSolidState()) {
            this.spawnAnim();
            this.setSolidState(true);
        }
        if(this.getSolidState()){
            this.getAttribute(Attributes.ARMOR).setBaseValue((double)(2+this.getAttribute(Attributes.ARMOR).getBaseValue()*1.5));
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue()/1.5));
        }
       }
    }
    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);
        if (flag && !this.getSolidState() && entity instanceof LivingEntity) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            entity.setSecondsOnFire((int)(f*2));
        }
        return flag;
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
    protected void checkFallDamage(double p_19911_, boolean p_19912_, BlockState p_19913_, BlockPos p_19914_) {return;}
    protected void dropEquipment() {
        super.dropEquipment();
        if(this.getSolidState()){
            this.spawnAtLocation(Items.BASALT);
         } else {
            this.spawnAtLocation(Items.MAGMA_CREAM);
        }
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
