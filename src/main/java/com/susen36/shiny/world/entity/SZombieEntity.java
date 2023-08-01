package com.susen36.shiny.world.entity;


import com.susen36.shiny.init.EntityInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolActions;

import javax.annotation.Nullable;


public class SZombieEntity extends Zombie {

    public SZombieEntity(EntityType<? extends Zombie> zombie, Level level) {
        super(zombie, level);
        if (!hasCustomName())
        {
            rename(this);
        }
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

               this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
               this.setDropChance(EquipmentSlot.OFFHAND, 0.0F);
               this.setDropChance(EquipmentSlot.HEAD, 0.0F);
               this.setDropChance(EquipmentSlot.CHEST, 0.0F);
               this.setDropChance(EquipmentSlot.LEGS, 0.0F);
               this.setDropChance(EquipmentSlot.FEET, 0.0F);
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
    @Nullable
    public Zombie getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
        return EntityInit.SHINY_ZOMBIE.get().create(p_149001_);
    }
}
