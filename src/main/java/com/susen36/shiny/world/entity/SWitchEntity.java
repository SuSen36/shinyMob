package com.susen36.shiny.world.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class SWitchEntity extends Witch {
    public SWitchEntity(EntityType<? extends Witch> type, Level level) {
        super(type, level);
    }
    public void tick(){
        super.tick();
        if(!this.level.isClientSide){
            this.setUsingItem(false);
        }
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY), SoundEvents.WANDERING_TRADER_DISAPPEARED, (p_289486_) -> {
            return  !p_289486_.isInvisible();
        }));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, new ItemStack(Items.GOLDEN_APPLE), SoundEvents.WANDERING_TRADER_REAPPEARED, (p_289487_) -> {
            return this.getHealth()<= 10 &&!this.isUsingItem();
        }));
    }
}
