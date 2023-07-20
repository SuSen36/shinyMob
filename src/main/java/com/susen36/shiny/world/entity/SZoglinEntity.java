package com.susen36.shiny.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.level.Level;

public class SZoglinEntity extends Zoglin {
    public SZoglinEntity(EntityType<? extends Zoglin> p_34204_, Level level) {
        super(p_34204_, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, (double)0.25F).add(Attributes.KNOCKBACK_RESISTANCE, (double)0.8F).add(Attributes.ATTACK_KNOCKBACK, 0.8D).add(Attributes.ATTACK_DAMAGE, 6.0D);
    }
}
