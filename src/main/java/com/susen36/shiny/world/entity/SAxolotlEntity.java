package com.susen36.shiny.world.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SAxolotlEntity extends Axolotl {
    public SAxolotlEntity(EntityType<? extends Axolotl> type, Level level) {
        super(type, level);
    }
    @Override
    public void applySupportingEffects(Player player) {
        MobEffectInstance mobeffectinstance = player.getEffect(MobEffects.REGENERATION);
        if (mobeffectinstance == null || mobeffectinstance.endsWithin(2699)) {
            int i = mobeffectinstance != null ? mobeffectinstance.getDuration() : 0;
            int j = Math.min(2700, 100 + i);
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, j, 2), this);
        }

        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
    }
}
