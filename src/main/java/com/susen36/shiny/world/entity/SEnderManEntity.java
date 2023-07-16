package com.susen36.shiny.world.entity;

import com.susen36.shiny.world.entity.ai.LookAtStrongholdGoal;
import com.susen36.shiny.world.entity.ai.ScorePumpkinGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class SEnderManEntity extends EnderMan {
    public SEnderManEntity(EntityType<? extends EnderMan> p_32485_, Level p_32486_) {
        super(p_32485_, p_32486_);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtStrongholdGoal(this, 20));
        this.goalSelector.addGoal(3, new ScorePumpkinGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.level.dimension().equals(ServerLevel.OVERWORLD)) {
            this.spawnAtLocation(Items.ENDER_EYE);
        }
    }
    public boolean isOnFire() {
        return false;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 6.0D).add(Attributes.ATTACK_KNOCKBACK,5.0D).add(Attributes.FOLLOW_RANGE, 64.0D);
    }
}
