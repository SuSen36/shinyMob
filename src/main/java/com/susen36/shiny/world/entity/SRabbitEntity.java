package com.susen36.shiny.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;

public class SRabbitEntity extends Rabbit {
    public SRabbitEntity(EntityType<? extends Rabbit> type, Level level) {
        super(type, level);
        this.setVariant(Variant.EVIL);
    }
}
