package com.susen36.shiny.world.entity.ai;

import com.susen36.shiny.world.entity.SGuardianEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GuardianSwellEntity  extends Goal {
    private final SGuardianEntity guardian;
    @Nullable
    private LivingEntity target;

    public GuardianSwellEntity(SGuardianEntity guardian) {
        this.guardian = guardian;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.guardian.getTarget();
        return this.guardian.getSwellDir() > 0 || livingentity != null && this.guardian.distanceToSqr(livingentity) < 5.0D ;
    }

    public void start() {
        this.guardian.getNavigation().stop();
        this.target = this.guardian.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.target == null) {
            this.guardian.setSwellDir(-1);
        } else {
            this.guardian.setSwellDir(1);
        }
    }
}
