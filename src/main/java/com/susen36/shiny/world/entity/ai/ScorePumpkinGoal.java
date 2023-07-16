package com.susen36.shiny.world.entity.ai;

import com.susen36.shiny.world.entity.SEnderManEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ScorePumpkinGoal extends Goal {
    private final SEnderManEntity enderMan;
    public int chargeTime;

    public ScorePumpkinGoal(SEnderManEntity enderMan) {
        this.enderMan = enderMan;
    }

    public boolean canUse() {
        return this.enderMan.getTarget() != null;
    }

    public void start() {
        this.chargeTime = 0;
    }


    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.enderMan.getTarget();
        if (livingentity != null) {
            if (livingentity.distanceToSqr(this.enderMan) < 7.0 && this.enderMan.hasLineOfSight(livingentity)) {
                Level level = this.enderMan.level();
                ++this.chargeTime;
                if (this.chargeTime == 50) {
                   if (level.isClientSide) {
                       for(int i = 0; i < 5; ++i) {
                           livingentity.level().addParticle(ParticleTypes.PORTAL, livingentity.getRandomX(0.7D), livingentity.getRandomY() - 0.17D, livingentity.getRandomZ(0.7D), (livingentity.getRandom().nextDouble() - 0.5D) * 2.0D, -livingentity.getRandom().nextDouble(), (livingentity.getRandom().nextDouble() - 0.5D) * 4.7D);
                       }
                   }
                    if (!level.isClientSide) {
                        if(livingentity instanceof Player player&&player.getInventory().armor.get(3).isEmpty())
                            livingentity.setItemSlot(EquipmentSlot.HEAD, Blocks.CARVED_PUMPKIN.asItem().getDefaultInstance());
                    }

                }
                if (this.chargeTime == 74) {
                    if (!level.isClientSide) {
                        if(livingentity instanceof Player player&&player.getInventory().armor.get(3).is(Blocks.CARVED_PUMPKIN.asItem()))
                            ((Player) livingentity).getInventory().armor.get(3).setCount(0);
                    }
                    if (level.isClientSide) {
                        for(int i = 0; i < 3; ++i) {
                            livingentity.level().addParticle(ParticleTypes.PORTAL, livingentity.getRandomX(0.7D), livingentity.getRandomY() - 0.17D, livingentity.getRandomZ(0.7D), (livingentity.getRandom().nextDouble() - 0.5D) * 2.0D, -livingentity.getRandom().nextDouble(), (livingentity.getRandom().nextDouble() - 0.5D) * 4.7D);
                        }
                    }
                    this.chargeTime = -200;
                }
            } else if (this.chargeTime > 0) {
                --this.chargeTime;
            }
        }

    }
}
