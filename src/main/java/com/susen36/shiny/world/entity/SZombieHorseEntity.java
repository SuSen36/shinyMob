package com.susen36.shiny.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.level.Level;

public class SZombieHorseEntity extends ZombieHorse {
    public SZombieHorseEntity(EntityType<? extends ZombieHorse> type, Level level) {
        super(type, level);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }
    public boolean isPushedByFluid() {
        return !this.isSwimming();
    }
}
