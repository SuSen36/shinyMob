package com.susen36.shiny.world.entity.ai;

import com.susen36.shiny.world.entity.SWitherEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class WitherSwellGoal extends Goal {
    private final SWitherEntity wither;
    @Nullable
    private LivingEntity target;

    public WitherSwellGoal(SWitherEntity p_25919_) {
        this.wither = p_25919_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.wither.getTarget();
        return this.wither.getSwellDir() > 0 || livingentity != null && this.wither.distanceToSqr(livingentity) < 9.0D;
    }

    public void start() {
        this.wither.getNavigation().stop();
        this.target = this.wither.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.target == null) {
            this.wither.setSwellDir(-1);
        } else if (this.wither.distanceToSqr(this.target) > 49.0D) {
            this.wither.setSwellDir(-1);
        } else if (!this.wither.getSensing().hasLineOfSight(this.target)) {
            this.wither.setSwellDir(-1);
        } else {
            this.wither.setSwellDir(1);
        }
    }
}