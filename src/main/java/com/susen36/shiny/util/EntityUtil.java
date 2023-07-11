package com.susen36.shiny.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

public class EntityUtil {

    @NotNull
    public static AttributeSupplier.Builder getAttrBuilder(double atk, double hpMax, double sight, double speed, double kb, double kb_r) {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, atk)
                .add(Attributes.MAX_HEALTH, hpMax)
                .add(Attributes.FOLLOW_RANGE, sight)
                .add(Attributes.MOVEMENT_SPEED, speed)
                .add(Attributes.ATTACK_KNOCKBACK, kb)
                .add(Attributes.KNOCKBACK_RESISTANCE, kb_r);
    }
}
