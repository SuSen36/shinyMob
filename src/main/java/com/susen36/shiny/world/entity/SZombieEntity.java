package com.susen36.shiny.world.entity;


import com.susen36.shiny.util.EntityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolActions;


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
