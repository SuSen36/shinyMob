package com.susen36.shiny.world.entity;

import com.susen36.shiny.init.EntityInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SChickenEntity extends Chicken {
    public SChickenEntity(EntityType<? extends Chicken> type, Level level) {
        super(type, level);
    }
    @Override @Nonnull
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        }
        if (!this.level.isClientSide) {
            InteractionResult ret;
            if(this.isAlive()&&player.getFoodData().needsFood()&&!this.isBaby()) {
                player.getFoodData().eat(2, 0.3F);
                if(this.random.nextInt(3)==0) {
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0));
                }
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
    @Nullable
    public Chicken getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
        return EntityInit.SHINY_CHICKEN.get().create(p_149001_);
    }
}
