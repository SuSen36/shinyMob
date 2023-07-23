package com.susen36.shiny.world.entity.ai;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SquidFlyGoal extends Goal {
    private final GlowSquid squid;

    public SquidFlyGoal(GlowSquid squid) {
        this.squid = squid;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        MoveControl movecontrol = this.squid.getMoveControl();
        if (!movecontrol.hasWanted()) {
            return true;
        } else {
            double d0 = movecontrol.getWantedX() - this.squid.getX();
            double d1 = movecontrol.getWantedY() - this.squid.getY();
            double d2 = movecontrol.getWantedZ() - this.squid.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0D || d3 > 3600.0D;
        }
    }

    public boolean canContinueToUse() {
        return false;
    }

    public void start() {
        RandomSource randomsource = this.squid.getRandom();
        double d0 = this.squid.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d1 = this.squid.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d2 = this.squid.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
        this.squid.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
    }
}