package com.susen36.shiny.world.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class NearestAttackableColorGoal<T extends Sheep> extends TargetGoal {
    protected final Class<T> targetType;
    protected final int randomInterval;
    @Nullable
    protected Sheep target;
    protected TargetingConditions targetConditions;

    public NearestAttackableColorGoal(Mob mob, Class<T> p_26061_, boolean p_26062_) {
        this(mob, p_26061_, 10, p_26062_, false, (Predicate<LivingEntity>) null);
    }

    public NearestAttackableColorGoal(Mob p_26053_, Class<T> p_26054_, int p_26055_, boolean p_26056_, boolean p_26057_, @Nullable Predicate<LivingEntity> p_26058_) {
        super(p_26053_, p_26056_, p_26057_);
        this.targetType = p_26054_;
        this.randomInterval = reducedTickDelay(p_26055_);
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(p_26058_);
    }

    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
            stop();
            return false;
        } else {
            this.findTarget();
            return true;
        }
    }

    protected AABB getTargetSearchArea(double p_26069_) {
        return this.mob.getBoundingBox().inflate(p_26069_, 4.0D, p_26069_);
    }

    protected void findTarget() {
            this.target = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (p_148152_) -> {
                return p_148152_ != null&&p_148152_.getColor()!=((Sheep)this.mob).getColor();
            }), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
    }

    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }

}