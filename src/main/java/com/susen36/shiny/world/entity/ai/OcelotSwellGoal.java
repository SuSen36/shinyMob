package com.susen36.shiny.world.entity.ai;

import com.susen36.shiny.world.entity.SOcelotEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class OcelotSwellGoal  extends Goal {
    private final SOcelotEntity creepercat;
    @Nullable
    private LivingEntity target;

    public OcelotSwellGoal(SOcelotEntity p_25919_) {
        this.creepercat = p_25919_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.creepercat.getTarget();
        return this.creepercat.getSwellDir() > 0 || livingentity != null && this.creepercat.distanceToSqr(livingentity) < 9.0D;
    }

    public void start() {
        this.creepercat.getNavigation().stop();
        this.target = this.creepercat.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.target == null) {
            this.creepercat.setSwellDir(-1);
        } else if (this.creepercat.distanceToSqr(this.target) > 49.0D) {
            this.creepercat.setSwellDir(-1);
        } else if (!this.creepercat.getSensing().hasLineOfSight(this.target)) {
            this.creepercat.setSwellDir(-1);
        } else {
            this.creepercat.setSwellDir(1);
        }
    }
}