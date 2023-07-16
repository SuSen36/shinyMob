package com.susen36.shiny.world.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Creeper;

public class FireWorkGoal extends Goal {
    private final Creeper creeper;

    public FireWorkGoal(Creeper p_25919_) {
        this.creeper = p_25919_;
    }

    public boolean canUse() {
        LivingEntity livingentity = this.creeper.getTarget();
        return this.creeper.getSwellDir() > 0 || livingentity != null && this.creeper.distanceToSqr(livingentity) < 9.0D;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {

        this.creeper.setSwellDir(1);

    }
}