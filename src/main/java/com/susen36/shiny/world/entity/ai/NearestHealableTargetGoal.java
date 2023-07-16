package com.susen36.shiny.world.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.raid.Raider;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class  NearestHealableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private static final int DEFAULT_COOLDOWN = 200;
    private int cooldown = 0;

    public NearestHealableTargetGoal(AbstractSkeleton skeleton, Class<T> p_26088_, boolean p_26089_, @Nullable Predicate<LivingEntity> p_26090_) {
        super(skeleton, p_26088_, 500, p_26089_, false, p_26090_);
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public void decrementCooldown() {
        --this.cooldown;
    }

    public boolean canUse() {
        if (this.cooldown <= 0 && this.mob.getRandom().nextBoolean()) {
            if (!((Raider)this.mob).hasActiveRaid()) {
                return false;
            } else {
                this.findTarget();
                return this.target != null;
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.cooldown = reducedTickDelay(200);
        super.start();
    }
}