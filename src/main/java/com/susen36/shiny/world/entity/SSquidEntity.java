package com.susen36.shiny.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SSquidEntity extends Squid {
    public SSquidEntity(EntityType<? extends Squid> type, Level level) {
        super(type, level);
    }
    protected void dropEquipment() {
        super.dropEquipment();
        this.spawnAtLocation(Items.NAUTILUS_SHELL);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED,0.1f).add(Attributes.ARMOR,6);
    }
}
