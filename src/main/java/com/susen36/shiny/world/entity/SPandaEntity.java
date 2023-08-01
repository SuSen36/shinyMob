package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SPandaEntity extends Panda {
    public SPandaEntity(EntityType<? extends Panda> type, Level level) {
        super(type, level);
    }
    @Nullable
    public Panda getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
        return EntityInit.SHINY_PANDA.get().create(p_149001_);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.13000000596046448).add(Attributes.ATTACK_DAMAGE, 10.0);
    }
}
