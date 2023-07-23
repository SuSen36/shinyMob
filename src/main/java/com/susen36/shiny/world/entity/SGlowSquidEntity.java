package com.susen36.shiny.world.entity;

import com.susen36.shiny.world.entity.ai.SquidFlyGoal;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SGlowSquidEntity extends GlowSquid {
    public SGlowSquidEntity(EntityType<? extends GlowSquid> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.moveControl = new SquidMoveControl(this);
    }
    protected void handleAirSupply(int p_30344_) {return;}
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new SquidFlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_289460_) -> {
            return Math.abs(p_289460_.getY() - this.getY()) <= 10.0D;
        }));
    }
    static class SquidMoveControl extends MoveControl {
        private final Squid squid;
        private int floatDuration;

        public SquidMoveControl(Squid squid) {
            super(squid);
            this.squid = squid;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.squid.getRandom().nextInt(10) + 5;
                    Vec3 vec3 = new Vec3(this.wantedX - this.squid.getX(), this.wantedY - this.squid.getY(), this.wantedZ - this.squid.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.squid.setDeltaMovement(this.squid.getDeltaMovement().add(vec3.scale(0.04D)));
                        float f = this.squid.getRandom().nextFloat() * ((float)Math.PI * 2F);
                        float f1 = Mth.cos(f) * 0.2F;
                        float f2 = -0.1F + this.squid.getRandom().nextFloat() * 0.2F;
                        float f3 = Mth.sin(f) * 0.2F;
                        this.squid.setMovementVector(f1, f2, f3);
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 p_32771_, int p_32772_) {
            AABB aabb = this.squid.getBoundingBox();

            for(int i = 1; i < p_32772_; ++i) {
                aabb = aabb.move(p_32771_);
                if (!this.squid.level().noCollision(this.squid, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }
}
