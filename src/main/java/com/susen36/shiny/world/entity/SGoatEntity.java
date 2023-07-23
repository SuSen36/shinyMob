package com.susen36.shiny.world.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class SGoatEntity extends Goat {
    public SGoatEntity(EntityType<? extends Goat> goat, Level level) {
        super(goat, level);
    }

    public SoundEvent getRandomHornSound(RandomSource randomSource) {
        int i = randomSource.nextInt(4);
        if (!this.isScreamingGoat()) {
            if (i == 0) {
                return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(0).get();
            } else if (i == 1) {
                return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(1).get();
            } else if (i == 2) {
                return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(2).get();
            } else if (i == 3) {
                return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(3).get();
            }
        } else if (i == 0) {
            return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(4).get();
        } else if (i == 1) {
            return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(5).get();
        } else if (i == 2) {
            return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(6).get();
        } else if (i == 3) {
            return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(7).get();
        }
        return SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(0).get();
    }
    public void aiStep() {
        if (this.level().random.nextInt(80) == 0) {
            int i = this.random.nextInt(16)+1;
            isRestless(this.level(), this);
            this.level.addParticle(ParticleTypes.NOTE, this.getX(), this.getY() + 1.2D, this.getZ() + 1.5D, (double)i / 12.0D, 0.0D, 0.0D);
        }
        super.aiStep();
    }
    public void isRestless(Level level, Entity entity) {
        if (entity.isAlive() && !entity.isSilent()) {
            List<Mob> list = level.getEntitiesOfClass(Mob.class, entity.getBoundingBox().inflate(50.0D), LivingEntity::isAlive);
            if (!list.isEmpty()) {
                Mob mob = list.get(level.random.nextInt(list.size()));
                if (!mob.isSilent()) {
                    SoundEvent soundevent = this.getRandomHornSound(this.random);
                    level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), soundevent, entity.getSoundSource(), 0.7F, getVoicePitch());
                }
            }
        }
    }
}
