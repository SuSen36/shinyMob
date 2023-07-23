package com.susen36.shiny.world.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class SSalmonEnity extends Salmon {
    public SSalmonEnity(EntityType<? extends Salmon> type, Level level) {
        super(type, level);
    }
    @Override @Nonnull
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        }
        if (!this.level.isClientSide) {
            InteractionResult ret;
            if(this.isAlive()&&player.getFoodData().needsFood()) {
                player.getFoodData().eat(2, 0.3F);
                this.remove(RemovalReason.DISCARDED);
                this.level.playSound(player,player.blockPosition(), SoundEvents.CAT_EAT, SoundSource.PLAYERS);
                ret = InteractionResult.CONSUME;
            } else {
                ret = InteractionResult.PASS;
            }
            return ret;
        }
        return InteractionResult.PASS;
    }
}
