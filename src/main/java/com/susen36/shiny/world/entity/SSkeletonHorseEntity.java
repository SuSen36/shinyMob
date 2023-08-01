package com.susen36.shiny.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.Level;

public class SSkeletonHorseEntity extends AbstractHorse {
    public SSkeletonHorseEntity(EntityType<? extends AbstractHorse> p_30894_, Level p_30895_) {
        super(p_30894_, p_30895_);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return createBaseHorseAttributes().add(Attributes.MAX_HEALTH, 17.0D).add(Attributes.MOVEMENT_SPEED, (double)0.25F);
    }
}
