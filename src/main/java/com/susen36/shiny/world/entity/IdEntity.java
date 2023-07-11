package com.susen36.shiny.world.entity;


import com.susen36.shiny.util.EntityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolActions;

import static net.minecraft.world.entity.animal.Wolf.PREY_SELECTOR;


public class IdEntity extends PathfinderMob {

    public IdEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        if (!hasCustomName())
        {
            rename(this);
        }
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, PREY_SELECTOR));
       }

    public void tick() {
        super.tick();
            LivingEntity target= this.getTarget();
        if (!this.level().isClientSide ) {
                if (target != null) {
                this.setItemSlot(EquipmentSlot.MAINHAND, target.getMainHandItem());
                this.setItemSlot(EquipmentSlot.OFFHAND, target.getOffhandItem());
                this.setItemSlot(EquipmentSlot.HEAD, target.getItemBySlot(EquipmentSlot.HEAD));
                this.setItemSlot(EquipmentSlot.CHEST, target.getItemBySlot(EquipmentSlot.CHEST));
                this.setItemSlot(EquipmentSlot.LEGS, target.getItemBySlot(EquipmentSlot.LEGS));
                this.setItemSlot(EquipmentSlot.FEET, target.getItemBySlot(EquipmentSlot.FEET));
            }
        }
    }

    private static void rename(Mob eventEntity) {

        Minecraft mc = Minecraft.getInstance();

        if (mc.getCameraEntity() instanceof AbstractClientPlayer client) {
            String  name = client.getScoreboardName();

        eventEntity.setCustomName(Component.literal(name));
        eventEntity.setCustomNameVisible(true);
        }
    }
    public boolean isBlocking() {
        return this.getMainHandItem().canPerformAction(ToolActions.SHIELD_BLOCK) || this.getOffhandItem().canPerformAction(ToolActions.SHIELD_BLOCK);
    }

    public boolean shouldShoot() {
        return this.getMainHandItem().getItem() instanceof ProjectileWeaponItem || this.getMainHandItem().getItem() instanceof TridentItem;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityUtil.getAttrBuilder(1,20,30,0.1,0,0);
    }
}
