package com.susen36.shiny.world.entity.ai;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.level.Level;

public class SFrogEntity extends Frog {
    public SFrogEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

}
