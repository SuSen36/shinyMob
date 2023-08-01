package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

public class SStriderEntity extends Strider {
    public SStriderEntity(EntityType<? extends Strider> type, Level level) {
        super(type, level);
    }
    public boolean canStandOnFluid(FluidState state) {
        return false;
    }
    public boolean isSaddleable() {
        return false;
    }
    public boolean hurt(DamageSource damageSource, float amount) {
        if(damageSource.is(DamageTypeTags.IS_EXPLOSION)){
            return false;
        }
        return super.hurt(damageSource,amount);
    }
    private SpawnGroupData spawnJockey(ServerLevelAccessor p_33882_, DifficultyInstance difficulty, Entity mob, @Nullable SpawnGroupData spawnGroupData) {
        mob.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
        this.finalizeSpawn(p_33882_, difficulty, MobSpawnType.JOCKEY, null, (CompoundTag)null);
        mob.startRiding(this, true);
        return new AgeableMob.AgeableMobGroupData(0.0F);
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33887_, DifficultyInstance p_33888_, MobSpawnType p_33889_, @Nullable SpawnGroupData p_33890_, @Nullable CompoundTag p_33891_) {
        if (this.isBaby()) {
            return super.finalizeSpawn(p_33887_, p_33888_, p_33889_, p_33890_, p_33891_);
        } else {
            RandomSource randomsource = p_33887_.getRandom();
            if (randomsource.nextInt(3) == 0) {
                EndCrystal crystal = EntityType.END_CRYSTAL.create(p_33887_.getLevel());

                if (crystal != null) {
                    crystal.setShowBottom(false);
                    p_33890_ = this.spawnJockey(p_33887_, p_33888_, crystal,  null);
                }
            } else {
                p_33890_ = new AgeableMob.AgeableMobGroupData(0.5F);
            }

            return super.finalizeSpawn(p_33887_, p_33888_, p_33889_, p_33890_, p_33891_);
        }
    }
    public boolean checkSpawnObstruction(LevelReader levelReader) {
        return level.dimension() == Level.END && this.position().horizontalDistanceSqr() < 40000;
    }
    @Nullable
    public Strider getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return EntityInit.SHINY_STRIDER.get().create(level);
    }
    protected void checkFallDamage(double p_19911_, boolean p_19912_, BlockState p_19913_, BlockPos p_19914_) {return;}
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.105F).add(Attributes.KNOCKBACK_RESISTANCE,10).add(Attributes.ARMOR,15).add(Attributes.FOLLOW_RANGE, 16.0D);
    }
}
