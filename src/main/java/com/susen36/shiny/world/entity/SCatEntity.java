package com.susen36.shiny.world.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class SCatEntity extends Cat {
    private static final Ingredient TEMPT_INGREDIENT = Ingredient.of(Items.COD, Items.SALMON);
    public SCatEntity(EntityType<? extends Cat> type, Level level) {
        super(type, level);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new Cat.CatTemptGoal(this, 0.6D, TEMPT_INGREDIENT, true));
        this.goalSelector.addGoal(5, new CatLieOnBedGoal(this, 1.1D, 8));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
        this.goalSelector.addGoal(7, new CatSitOnBlockGoal(this, 0.8D));
        this.goalSelector.addGoal(8, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(10, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(11, new WaterAvoidingRandomStrollGoal(this, 0.8D, 1.0000001E-5F));
        this.goalSelector.addGoal(12, new LookAtPlayerGoal(this, Player.class, 10.0F));
       }
    @Override @Nonnull
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        }
        if (!this.level.isClientSide) {
            InteractionResult ret;
            if(this.isAlive()&&player.getFoodData().needsFood()) {
                player.getFoodData().eat(5, 0.5F);
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,2000));
                if(random.nextInt(3)==0){
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,2000));
                }else {
                    player.addEffect(new MobEffectInstance(MobEffects.SATURATION,200));
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
    public boolean isFood(ItemStack p_28177_) {
        return TEMPT_INGREDIENT.test(p_28177_);
    }
}
