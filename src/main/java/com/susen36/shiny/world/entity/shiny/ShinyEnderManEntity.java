package com.susen36.shiny.world.entity.shiny;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class ShinyEnderManEntity extends EnderMan {
    public ShinyEnderManEntity(EntityType<? extends EnderMan> p_32485_, Level p_32486_) {
        super(p_32485_, p_32486_);
        this.blocksBuilding = true;
        this.setPathfindingMalus(BlockPathTypes.WATER, 1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 0.0F);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new LookAtStrongholdGoal(this, 20.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
    }

    public boolean canStandOnFluid(FluidState fluidState) {
        return true;
    }

    public void tick() {
        super.tick();
        this.floatStrider();
        this.checkInsideBlocks();
    }
    public void aiStep() {

        this.jumping = false;
        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
        if (this.level instanceof ServerLevel) {
            ServerLevel serverlevel = (ServerLevel) level;
            BlockPos blockpos = serverlevel.findNearestMapStructure(StructureTags.EYE_OF_ENDER_LOCATED, this.blockPosition(), 100, false);
            this.getLookControl().setLookAt(blockpos.getX(), this.getEyeY(), blockpos.getZ());
        }

        super.aiStep();
    }

    protected void checkFallDamage(double p_33870_, boolean p_33871_, BlockState state, BlockPos pos) {
        this.checkInsideBlocks();
        if (this.isInFluidType()) {
            this.resetFallDistance();
        } else {
            super.checkFallDamage(p_33870_, p_33871_, state, pos);
        }
    }
    private void floatStrider() {
        if (this.isInFluidType()) {
           if (  this.level().getFluidState(this.blockPosition().above()).isEmpty()) {
                this.setOnGround(true);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5).add(0.0, 0.05, 0.0));
            }
        }
    }
    public float getWalkTargetValue(BlockPos pos, LevelReader levelReader) {
        if (!levelReader.getBlockState(pos).getFluidState().isEmpty()){
            return 10.0F;
        } else {
            return this.isInFluidType() ? Float.NEGATIVE_INFINITY : 0.0F;
        }
    }
    public boolean isOnFire() {
        return false;
    }

    public boolean isSensitiveToWater() {
        return !isInWater();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 7.0D).add(Attributes.FOLLOW_RANGE, 64.0D);
    }
    class LookAtStrongholdGoal extends Goal {
        protected final Mob mob;
        @Nullable
        protected BlockPos lookAt;
        private int lookTime;
        protected final float probability;
        protected final TargetingConditions lookAtContext;

        public LookAtStrongholdGoal(Mob mob, float probability) {
            this.mob = mob;
            this.probability = probability;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
            this.lookAtContext = TargetingConditions.forNonCombat();
        }

        public boolean canUse() {
            if (this.mob.getRandom().nextFloat() >= this.probability) {
                return false;
            } else {
                this.lookAt = ((ServerLevel) this.mob.level).findNearestMapStructure(StructureTags.EYE_OF_ENDER_LOCATED, this.mob.blockPosition(), 100, false);
                return true;
            }
        }

        public boolean canContinueToUse() {
            if (this.lookAt==null||this.mob.getTarget()!=null) {
                return false;
            } else if (this.mob.distanceToSqr(this.lookAt.getCenter()) > (double)(9999)) {
                return false;
            } else {
                return this.lookTime > 0;
            }
        }

        public void start() {
            this.lookTime = this.adjustedTickDelay(80 + this.mob.getRandom().nextInt(80));
        }

        public void tick() {
                if (this.lookAt != null) {
                    --this.lookTime;
                   // double d0 = this.mob.distanceToSqr(this.lookAt.getX(), this.mob.getY(), this.mob.getZ()) <= 30 ? this.lookAt.getY() : this.mob.getEyeY();
                    this.mob.getLookControl().setLookAt(this.lookAt.getX(), this.mob.getEyeHeight(), this.lookAt.getZ());
                }
            }
        }

}



