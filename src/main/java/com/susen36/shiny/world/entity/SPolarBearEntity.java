package com.susen36.shiny.world.entity;

import com.susen36.shiny.world.entity.ai.BearBeehiveGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SPolarBearEntity extends PolarBear {
    public SPolarBearEntity(EntityType<? extends PolarBear> type, Level level) {
        super(type, level);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new BearBeehiveGoal(this));
    }
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if (entity instanceof Bee) {
                amount = amount / 3.0F;
            }
            return super.hurt(source, amount);
        }
    }
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.HONEYCOMB);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 25.0D).add(Attributes.FOLLOW_RANGE, 30.0D).add(Attributes.MOVEMENT_SPEED, 0.27D).add(Attributes.ATTACK_DAMAGE, 8.0D);
    }
}
