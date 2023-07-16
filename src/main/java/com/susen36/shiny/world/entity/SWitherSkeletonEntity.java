package com.susen36.shiny.world.entity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SWitherSkeletonEntity extends WitherSkeleton {

    private final RangedBowAttackGoal<AbstractSkeleton> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false);
    public SWitherSkeletonEntity(EntityType<? extends WitherSkeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();

        if(!level.isClientSide) {
            LivingEntity target = this.getTarget();

            if (target != null && this.distanceToSqr(target) >= 15.0) {
                useBow();
            }else {useAxe();}
        }
    }
    private void useBow() {
        ItemStack bow = Items.BOW.getDefaultInstance();
        this.setItemInHand(InteractionHand.MAIN_HAND, bow);
        this.goalSelector.removeGoal(this.meleeGoal);
        this.goalSelector.addGoal(4, this.bowGoal);
    }
    private void useAxe() {
        ItemStack sword = Items.STONE_AXE.getDefaultInstance();
        this.setItemInHand(InteractionHand.MAIN_HAND, sword);
        this.goalSelector.removeGoal(this.bowGoal);
        this.goalSelector.addGoal(4, this.meleeGoal);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.26D);
    }
}
