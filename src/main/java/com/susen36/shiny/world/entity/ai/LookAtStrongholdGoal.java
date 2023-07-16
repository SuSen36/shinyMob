package com.susen36.shiny.world.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class LookAtStrongholdGoal extends Goal {
    protected final Mob mob;
    @Nullable
    protected BlockPos lookAt;
    private int lookTime;
    protected final float probability;
    protected final TargetingConditions lookAtContext;

    public LookAtStrongholdGoal(Mob mob, float probability) {
        this.mob = mob;
        this.probability = probability;
        this.setFlags(EnumSet.of(Flag.LOOK));
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
            double d0 = this.mob.distanceToSqr(this.lookAt.getX(), this.mob.getY(), this.mob.getZ()) <= 30 ? this.lookAt.getY() : this.mob.getEyeY();
            this.mob.getLookControl().setLookAt(this.lookAt.getX(), d0, this.lookAt.getZ());
        }
    }
}