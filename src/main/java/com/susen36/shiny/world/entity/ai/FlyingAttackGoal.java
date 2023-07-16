package com.susen36.shiny.world.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FlyingAttackGoal  extends Goal {
    protected final Mob mob;
    public FlyingAttackGoal(Mob mob) {
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.mob=mob;
    }

    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive() && !this.mob.getMoveControl().hasWanted() && this.mob.level.random.nextInt(reducedTickDelay(5)) == 0) {
            return this.mob.distanceToSqr(livingentity) > this.mob.getBoundingBox().getSize();
        } else {
            return false;
        }
    }

    public boolean canContinueToUse() {
        return this.mob.getMoveControl().hasWanted() && this.mob.getTarget() != null && this.mob.getTarget().isAlive();
    }

    public void start() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null) {
            Vec3 vec3 = livingentity.getEyePosition();
            this.mob.getMoveControl().setWantedPosition(vec3.x, vec3.y, vec3.z, 1.2f);
        }
    }


    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null) {
            if (this.mob.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                this.mob.doHurtTarget(livingentity);
            } else {
                double d0 = this.mob.distanceToSqr(livingentity);
                if (d0 < 9.0) {
                    Vec3 vec3 = livingentity.getEyePosition();
                    this.mob.getMoveControl().setWantedPosition(vec3.x, vec3.y, vec3.z, 1.2f);
                }
            }
        }

    }
}